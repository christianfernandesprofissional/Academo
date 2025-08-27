package com.academo.service.typeActivity;

import com.academo.model.TypeActivity;

import java.util.List;

public interface ITypeActivityService {

    public List<TypeActivity> findAll();
    public TypeActivity findById(Integer id);
    public TypeActivity create(TypeActivity typeActivity);
    public TypeActivity update(TypeActivity typeActivity);

}
