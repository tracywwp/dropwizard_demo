package com.tracy.dropwizard.resource;

import com.tracy.dropwizard.vo.Blog;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 11:02
 * 请求类
 */
@Path("/")
public class IndexResource {

    private JacksonDBCollection<Blog, String> collection;

    public IndexResource(JacksonDBCollection<Blog, String> blogs) {
        this.collection = blogs;
    }

    /**
     * GET请求指向“/” URL时会被调用的源
     * 创建一个新的JAX-RS源
     * @return
     */
    @GET
    @Produces(value= MediaType.APPLICATION_JSON)//返回json数据
    @Timed//metrics中的 执行时间 度量
    public List<Blog> index(){
//        return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
//                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
        DBCursor<Blog> dbCursor = collection.find();
        List<Blog> blogs = new ArrayList<>();
        while (dbCursor.hasNext()) {
            Blog blog = dbCursor.next();
            blogs.add(blog);
        }
        return blogs;
    }


}
