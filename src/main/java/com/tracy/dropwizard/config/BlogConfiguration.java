package com.tracy.dropwizard.config;

import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 10:45
 * 环境参数配置类
 * MongoDB数据库的详细信息
 */


public class BlogConfiguration extends Configuration{
    @JsonProperty
    @NotEmpty
    public String mongohost = "localhost";

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "mydb";
}
