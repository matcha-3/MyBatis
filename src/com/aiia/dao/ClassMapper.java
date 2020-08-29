package com.aiia.dao;

import com.aiia.pojo.Classes;

public interface ClassMapper {
    public Classes getClasses(int id);
    public Classes getClasses2(int id);
    public void  updateClasses(Classes classes);
}
