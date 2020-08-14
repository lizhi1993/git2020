package com.lanxin.unit;

import com.baomidou.mybatisplus.core.MybatisSessionFactoryBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.lanxin.dao.EmpDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;


public class SqlSessionFacory {

    public static SqlSession getSqlSession() throws IOException {

       SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();


        SqlSession sqlSession = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("config.xml")).openSession();

        return sqlSession;

    }

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = SqlSessionFacory.getSqlSession();

        EmpDao mapper = sqlSession.getMapper(EmpDao.class);

        String name = mapper.selectEmpName("张三");

        System.out.println(name);

    }
}
