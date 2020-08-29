package com.aiia.dao;

import com.aiia.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public List<User>getUserList();
    public int getUserCount();
    public List<User> getUserListByUserName(String userName);
    public List<User> getUserListByUserPojo(User user);

    public List<User>getUserListByUserPojoWith(User user);
    public List<User>getUserListByUserPojoWithWhere(User user);
    public List<User>getUserListByUserPojoWithTrim(User user);

    public List<User>getUserListByIdListWithForeach(List<Integer>idList);
    public List<User>getUserListByIdArrayWithForeach(int[] idArray);
    public List<User>getUserListByIdMapWithForeach(@Param(value = "idMaps") Map<Integer,Integer> idMap);

    public List<User>getUserListByUserPojoWithChoose(User user);

    public List<User>getUserListByMap(Map<String,String>map);
    public List<User>getUserWithRoleNameListByUserPojo(User user);

    public List<User>getUserListBySql();
    public List<User>getUserListBySql2(int id);

    //DML操作
    public void addUser(User user);
    public int modifyUser(User user);

    public int modifyUserWithSet(User user);
    public int modifyUserWithTrim(User user);

    public int deleteUserById(int id);
    //@Param()指定参数在xml配置文件中对应的sql语句占位符的标志
    public void updatePwd(@Param("id")Integer id,@Param("userPassword")String pwd);
}
