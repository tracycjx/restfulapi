package com.vancl.xmw.domain;

import org.apache.log4j.Logger;

public class User{
    private static final Logger logger = Logger.getLogger(User.class);
    private  int userId;
    private  String userName;
    public  void setUserId(int userId){ this.userId=userId;}
    public  int getUserId(){ return  userId;}

    public  void  setUserName(String userName){this.userName=userName;}
    public  String getUserName(){return  this.userName;}
}