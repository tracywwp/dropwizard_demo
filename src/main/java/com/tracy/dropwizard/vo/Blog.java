package com.tracy.dropwizard.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 11:08
 */
import java.util.Date;


public class Blog {

    private String id = UUID.randomUUID().toString();

    //不能为空
    @NotBlank
    private String title;


    @URL//字段验证注解 合法的url
    @NotBlank
    private String url;

    private final Date publishedOn = new Date();

    public Blog() {
    }

    public Blog(String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }
}
