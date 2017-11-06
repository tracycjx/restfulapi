package com.vancl.xmw.resource;

import com.vancl.xmw.domain.User;
import com.vancl.xmw.serviceImpl.UserMapperImpl;
import org.apache.log4j.Logger;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class MyResource  {
    private static final Logger logger = Logger.getLogger(MyResource.class);
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/username/{userId}")
    public String getUserId(@PathParam("userId") int userId) {
        User u=    new UserMapperImpl().getUser(userId);
        return  u.getUserName();
    }

    /**
     * @param userId
     * @return
     */
    @GET
    @Path("/user/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User GetUser(@PathParam("userId") int userId) {

         User user=  new UserMapperImpl().getUser(userId);
        logger.debug("get user id is " +user.getUserId()+" get username is " +user.getUserName());
         return user;
    }

    /**
     * @param userName
     * @return
     */
    @GET
    @Path("/adduser")
    public  int AddUser(@QueryParam("userName") String userName) {

         int userId= new UserMapperImpl().insertUser(userName);
         logger.debug("addUser id is" +userId);
         return  userId;
    }

    @GET
    @Path("/hello/{param}")
    @Produces("text/plain;charset=UTF-8")
    public String sayHelloToUTF8(@PathParam("param") String username) {
        return "Hello " + username;
    }

    @GET
    @Path("/user")
    @Produces("text/plain")
    public User getUser(@QueryParam("name") String name, @DefaultValue("26") @QueryParam("age") int age)
    {
        return new UserMapperImpl().getUser(age);
    }
}
