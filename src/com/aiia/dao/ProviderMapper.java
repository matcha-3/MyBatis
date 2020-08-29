package com.aiia.dao;

import com.aiia.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    /**
     *查询供应商表记录数
     *@return
     */
    public int count();
    /**
     *查询供应商列表
     *@return
     */
    public List<Provider> getProviderList();
    /**
     * 根据供应商名称查询供应商列表(模糊查询)
     * @param proName
     * @return
     */
    public List<Provider>getProviderListByProName(String proName);
    /**
     * 增加供应商
     * @param provider
     * @return
     */

    public int add(Provider provider);
    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    public int modify(Provider provider);
    /**
     * 根据供应商ID删除供应商信息
     * @param delId
     * @return
     */
    public int deleteProviderById(@Param("id")Integer delId);
}
