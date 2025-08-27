package com.academo.service.typeActivity;

import com.academo.model.TypeActivity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeActivityServiceImp implements ITypeActivityService{
    @Override
    public List<TypeActivity> findAll() {
        return List.of();
    }

    @Override
    public TypeActivity findById(Integer id) {
        return null;
    }

    @Override
    public TypeActivity create(TypeActivity typeActivity) {
        return null;
    }

    @Override
    public TypeActivity update(TypeActivity typeActivity) {
        return null;
    }
}
