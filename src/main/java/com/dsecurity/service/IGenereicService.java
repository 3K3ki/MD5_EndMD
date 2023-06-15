package com.dsecurity.service;

import java.util.List;

public interface IGenereicService<T,E>{
    List<T> findAll();
    T save(T t);
    void deleteById(E id);
    T findById(E id);
    T update(Long id,T t);
}
