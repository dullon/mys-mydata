package com.learn.chaptertest.main;

import java.io.IOException;

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

        Logger logger = LogManager.getLogger(module);
    	BasicConfigurator.configure();
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = new Role();
            role.setRoleName("张三");
            role.setNote("这个详细信息不清楚");
            roleMapper.insertRole(role);
            Role role2 = roleMapper.getRole(10L);
            sqlSession.commit();
            System.out.println(role2.getId()+"<>"+role2.getRoleName()+"<>"+role2.getNote());
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
