package com.tracy.dropwizard.managed;

import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 15:31
 * 在应用程序启动和停止时管理程序资源
 */
public class MongoManaged implements Managed {

    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {

    }

    //关闭了MongoDB连接
    @Override
    public void stop() throws Exception {
       mongo.close();
    }
}
