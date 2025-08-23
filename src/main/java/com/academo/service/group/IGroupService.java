package com.academo.service.group;

import com.academo.model.Group;

import java.util.List;

public interface IGroupService{

    public List<Group> findAll();
    public Group findById(Integer id);
    public Group create(Group group);
    public Group update(Group group);

}
