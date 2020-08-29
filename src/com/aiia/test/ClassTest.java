package com.aiia.test;

import com.aiia.dao.ClassMapper;
import com.aiia.pojo.Classes;
import com.aiia.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ClassTest {
    public static void main(String[]args){
        ClassTest classTest=new ClassTest();
        classTest.testAssociation();
    }
    //一对一测试
    public void testAssociation(){
        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        ClassMapper classMapper=sqlSession.getMapper(ClassMapper.class);

        //第一次查询，发出sql语句，并将查询结果放入缓存中
        Classes classes=classMapper.getClasses(1);
        System.out.println(classes);
        // 在两次查询中，加入一次DML操作并提交
        classMapper.updateClasses(classes);
        sqlSession.commit();
        //第二次查询，由于是同一个sqlSession,会在缓存中查找查询结果
        //如果有，则直接从缓存中取出来，不和数据库进行交互
        Classes classes2 = classMapper.getClasses(1);

        System.out.println(classes2);

		/*

		Classes classes2 = classMapper.getClasses2(1);

		System.out.println(classes2.getName());

		System.out.println(classes2);

		*/

        MyBatisUtil.closeSqlSession(sqlSession);

    }
}
