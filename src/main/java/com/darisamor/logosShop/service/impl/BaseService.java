package com.darisamor.logosShop.service.impl;

import java.util.List;

public interface BaseService<T> {
    T create(T t);
    T update(T t);
    T delete(T t);
    T deleteById(Long id);
    T getEntity(Long id);
    List<T> getAll();
}
