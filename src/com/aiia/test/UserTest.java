package com.aiia.test;

import com.aiia.dao.UserMapper;
import com.aiia.pojo.User;
import com.aiia.util.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserTest {
    public static void main(String[]args){
        UserTest userTest=new UserTest();
        //userTest.testSelect();
        //userTest.testDml();
        //userTest.testDynamicSql();
        //userTest.testDynamicSqlForeach();
        //userTest.testDynamicSqlChoose();
        //userTest.testDynamicSqlIncludeSql();
        userTest.testMyBatisPageHelper();
    }
    public void testSelect(){
        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        //调用userMapper的SQL语句查询结果
        List<User> users=userMapper.getUserList();
        int count =userMapper.getUserCount();
        List<User> userlist=userMapper.getUserListByUserName("孙");

        User user=new User();
        user.setUserName("孙");
        user.setUserRole(3);

        Map<String, String> map = new HashMap<String, String>();
        map.put("uName", "超");
        map.put("uRole", "3");

        List<User> userlist2 = userMapper.getUserListByUserPojo(user);
        List<User> userlist3 = userMapper.getUserListByMap(map);

        System.out.println("users:" + users);
        System.out.println("count:" + count);
        System.out.println("userlist:" + userlist);

        System.out.println("userlist2:" + userlist2);
        System.out.println("userlist3:" + userlist3);

        // 调用userMapper的getUserWithRoleNameListByUserPojo方法返回自定义定制数据结果
        List<User> userlist4 = userMapper.getUserWithRoleNameListByUserPojo(user);

        System.out.println("userlist4:" + userlist4);

        MyBatisUtil.closeSqlSession(sqlSession);
    }
    public void testDml(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        //添加用户
        Date date=null;
        try {
            date=new SimpleDateFormat("yyyy-MM-dd").parse("1984-12-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user2 = new User(18, "zhaominX", "赵敏X", "0000000", 1, date, "18099897657", "北京市昌平区天通苑3区12号楼", 2, 1,
                new Date(), null, null);
        try {
            userMapper.addUser(user2);
            sqlSession.commit();
            System.out.println("userMapper.getUserCount():" + userMapper.getUserCount());
        } catch (Exception e) {
            System.out.println("DML操作异常回滚");
            sqlSession.rollback();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }

        User user3 = new User(18, "zhaominX", "赵敏X", "0000000", 1, date, "18099897657", "北京市昌平区天通苑3区12号楼", 2, 1,
                new Date(), 1, null);
            sqlSession=MyBatisUtil.getSqlSession();
            userMapper=sqlSession.getMapper(UserMapper.class);

            userMapper.modifyUser(user3);
            sqlSession.commit();

            int result =userMapper.deleteUserById(27);
        System.out.printf("删除了"+result+"条记录");
        sqlSession.commit();

        userMapper.updatePwd(18,"12345678");
        sqlSession.commit();

        MyBatisUtil.closeSqlSession(sqlSession);
    }
    //测试动态SQL的使用
    public void testDynamicSql(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        List<User>userList=userMapper.getUserListByUserPojo(new User());

        System.out.println(userList);

        User user=new User();
        User user2=new User();
        //user2.setUserName("赵");
        //user2.setUserRole(3);

        List<User>userList2=userMapper.getUserListByUserPojoWithWhere(user);
        List<User>userList3=userMapper.getUserListByUserPojoWithTrim(user2);

        System.out.println(userList2);
        System.out.println(userList3);

        user.setId(15);

        user.setUserName("赵敏Z");

        int result=userMapper.modifyUserWithSet(user);

        sqlSession.commit();

        int result2=userMapper.modifyUserWithSet(user);

        sqlSession.commit();

        System.out.println("Set成功修改了"+result+"条记录");
        System.out.println("Trim成功修改了"+result2+"条记录");

        MyBatisUtil.closeSqlSession(sqlSession);
    }
    public void testDynamicSqlForeach(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        List<Integer> idList= Arrays.asList(1,2,3,4,5,6);

        List<User> userList=userMapper.getUserListByIdListWithForeach(idList);
        int[]idArray={1,2,3,4,5,6};

        List<User>userList2=userMapper.getUserListByIdArrayWithForeach(idArray);

        Map<Integer,Integer>idMaps=new HashMap<>();

        idMaps.put(1000,1);
        idMaps.put(1001,2);
        idMaps.put(1002,3);
        idMaps.put(1004,4);
        idMaps.put(1005,5);
        idMaps.put(1006,6);

        List<User> userList3=userMapper.getUserListByIdMapWithForeach(idMaps);
        System.out.println(userList);
        System.out.println(userList2);
        System.out.println(userList3);

        MyBatisUtil.closeSqlSession(sqlSession);

    }
    public void testDynamicSqlChoose(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user=new User();
        user.setUserName("赵");
        user.setUserRole(2);

        List<User> userList=userMapper.getUserListByUserPojoWithChoose(user);

        System.out.println(userList);
        MyBatisUtil.closeSqlSession(sqlSession);

    }
    public void testDynamicSqlIncludeSql(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        List<User>userList=userMapper.getUserListBySql();
        List<User>userList2=userMapper.getUserListBySql2(1);

        System.out.println(userList);
        System.out.println(userList2);
        MyBatisUtil.closeSqlSession(sqlSession);
    }
    public void testMyBatisPageHelper(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(1, 10);

        List<User> userlist = userMapper.getUserList();

        //用PageInfo对结果进行包装
        PageInfo<User> page = new PageInfo<User>(userlist);

        System.out.println(userlist);

        System.out.println(page.getPageNum());
        System.out.println(page.getPageSize());
        System.out.println(page.getStartRow());
        System.out.println(page.getEndRow());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.isHasPreviousPage());
        System.out.println(page.isHasNextPage());
    }

}
