package com.github.eipai.codemo.service;

import com.github.eipai.codemo.domain.WebUser;

public interface WebUserService {

    WebUser getById(String id);

    void transDemo(String id);
}
