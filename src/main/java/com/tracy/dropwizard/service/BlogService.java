package com.tracy.dropwizard.service;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.tracy.dropwizard.check.MongoHealthCheck;
import com.tracy.dropwizard.config.BlogConfiguration;
import com.tracy.dropwizard.managed.MongoManaged;
import com.tracy.dropwizard.resource.BlogResource;
import com.tracy.dropwizard.resource.IndexResource;
import com.tracy.dropwizard.vo.Blog;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 10:52
 * 将各种提供基本功能的捆绑和命令集合在一块，
 * 它还启动嵌入式Jetty服务器
 */
public class BlogService extends Service<BlogConfiguration>{

    /**
     *
     * @param args
     * @throws Exception
     * 创建BlogService的实例,并调用run方法
     * 将服务器命令作为参数传递，服务器命令将启动嵌入式Jetty服务器
     */
    public static void main(String[] args) throws Exception{
        //主程序运行
        //1,http://localhost:8080/ 返回json数据成功
        //2.http://localhost:8081/healthcheck 健康检查页面去检验MongoDB是否在运行
        new BlogService().run(new String[]{"server"});
    }

    /**
     * 初始化方法在服务运行方法之前被调用
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.setName("blog");
    }

    /**
     * 服务运行时将调用它的run方法
     * @param configuration
     * @param environment
     * @throws Exception
     */
    @Override
    public void run(BlogConfiguration configuration, Environment environment) throws Exception {
        //MongoDB的配置包含进来
        //1,使用BlogConfiguration对象创建了一个新的Mongo实例
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        //2.一个新的MongoManaged实例被创建并添加到环境中
        environment.manage(mongoManaged);
        //3.健康检查被添加
        environment.addHealthCheck(new MongoHealthCheck(mongo));
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Blog, String> blogs = JacksonDBCollection.wrap(db.getCollection("blogs"), Blog.class, String.class);
        //注册IndexResource
        environment.addResource(new IndexResource(blogs));

        //注册BlogResource
        environment.addResource(new BlogResource(blogs));
    }


}
