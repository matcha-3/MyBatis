package com.aiia.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory=null;

    static {
        //在静态代码块下，factory只会被创建一次
        if(sqlSessionFactory==null){
            //mybatis的配置文件
            String resource= "com/aiia/conf/conf.xml";
            // 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
            InputStream inputStream=MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
            // 获取SqlSessionFactory
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    public static SqlSession getSqlSession(boolean autoCommit){
        return sqlSessionFactory.openSession(autoCommit);
    }
    public static void closeSqlSession(SqlSession sqlSession) {
        sqlSession.close();
    }
}
