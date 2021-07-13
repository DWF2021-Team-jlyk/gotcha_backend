package com.gotcha.www.extra.dao;

import org.apache.ibatis.annotations.Select;

public interface extraDAO {
    @Select("select sysdate form dual")
    String getTimeFromDataBase();
}
