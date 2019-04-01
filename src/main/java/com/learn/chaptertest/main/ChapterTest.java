package com.learn.chaptertest.main;

import java.io.IOException;
import java.util.List;

import com.learn.chaptertest.service2.PhoneNumber11;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.learn.chaptertest.mapper.RoleMapper;
import com.learn.chaptertest.po.Role;
import com.learn.chaptertest.util.SqlSessionFactoryUtil;

public class ChapterTest {
    public static final String module = ChapterTest.class.getName();
    public static void main(String [] args) throws IOException {

       /* Logger logger = LogManager.getLogger(module);
        PhoneNumber11 phoneNumber11 = new PhoneNumber11((short) 222,(short)333,(short)4444);
        System.out.println(phoneNumber11.getClass() == phoneNumber11.clone().getClass()); //true
        System.out.println(phoneNumber11 != phoneNumber11.clone());//true
        System.out.println(phoneNumber11.hashCode() == phoneNumber11.clone().hashCode());//true
        System.out.println(phoneNumber11.clone().equals(phoneNumber11));//true*/
    	//BasicConfigurator.configure();
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = new Role();
            role.setRoleName("张三");
            role.setNote("这个详细信息不清楚");
            //roleMapper.insertRole(role);
            //Role role2 = roleMapper.getRole(10L);
            List<Role> role1 = roleMapper.find(10L);
            sqlSession.commit();
            //System.out.println(role2.get(0).getId()+"<>"+role2.get(0).getRoleName()+"<>"+role2.get(0).getNote());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sqlSession.rollback();
            //e.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
