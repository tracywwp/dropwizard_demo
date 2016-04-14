package com.tracy.dropwizard.check;

import com.mongodb.Mongo;
import com.yammer.metrics.core.HealthCheck;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 15:35
 * 检查MongoDB的连接与否
 */
public class MongoHealthCheck extends HealthCheck{
    private Mongo mongo;

    public MongoHealthCheck(Mongo mongo) {
        super("MongoDBHealthCheck");
        this.mongo = mongo;
    }


    @Override
    protected Result check() throws Exception {
        mongo.getDatabaseNames();
        return Result.healthy();
    }
}
