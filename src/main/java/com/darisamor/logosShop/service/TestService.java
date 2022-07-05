package com.darisamor.logosShop.service;

import com.darisamor.logosShop.domain.TestDTO;

public interface TestService extends BaseService<TestDTO> {
    TestDTO findByName(String name);
}