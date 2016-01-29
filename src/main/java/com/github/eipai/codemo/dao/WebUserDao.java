package com.github.eipai.codemo.dao;

import org.apache.ibatis.annotations.Param;

import com.github.eipai.codemo.domain.WebUser;

public interface WebUserDao {

    WebUser get(String userId);

    void update(@Param("webUser") WebUser webUser);

    void resetRemark(@Param("webUser") WebUser webUser);

}
