package com.tracy.dropwizard.resource;

import com.tracy.dropwizard.vo.Blog;
import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.JacksonDBCollection;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: shuyun
 * Date: 2016/4/12
 * Time: 15:45
 * 负责创建博客条目
 *
 */
@Path("/blogs")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)//消费数据格式
public class BlogResource {
   private JacksonDBCollection<Blog,String>collection;

    public BlogResource(JacksonDBCollection<Blog, String> collection) {
        this.collection = collection;
    }

    @POST
    @Timed
    public Response publishNewBlog(@Valid Blog blog){
        //添加blog
        collection.insert(blog);
        //返回数据
        return Response.noContent().build();
    }
}
