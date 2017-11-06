package com.vancl.xmw.serviceImpl;

import com.vancl.xmw.domain.User;
import com.vancl.xmw.service.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class UserMapperImpl extends IOException implements UserMapper {

    private static final Logger logger = Logger.getLogger(UserMapperImpl.class);
    public User getUser(int userId) {

        try {
            String resource = "config/mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            String statement = "com.vancl.xmw.sqlmap.user.getUser";//映射sql的标识字符串
            try {
                User u = session.selectOne(statement, userId);
                return u;

            }
            catch (Exception e)
            {
                logger.debug("Exception msg is  "+e.getStackTrace());
            }
            finally {
                session.close();
                logger.debug("finally   session close " );
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("IOException   " +e.getMessage());
        }
        return  null;
    }

    public int   insertUser(String userName) {

        try {
            String resource = "config/mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sessionFactory.openSession();
            try {
                User user=new User();
                user.setUserName(userName);
                //session.insert("com.vancl.xmw.sqlmap.user.addUser",user);
                session.insert("com.vancl.xmw.sqlmap.user.addUser-autoID",user);
                session.commit();
                return  user.getUserId();
            }
            catch (Exception e)
            {
                logger.debug("Exception msg is  "+e.getMessage());
            }
            finally {
                session.close();
                logger.debug("finally   session close " );
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("IOException   " +e.getMessage());
        }
        return 0;
    }
}