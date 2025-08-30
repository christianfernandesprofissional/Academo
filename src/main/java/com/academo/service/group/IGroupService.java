package com.academo.service.group;

import com.academo.model.Group;

import java.util.List;

public interface IGroupService{

    public List<Group> getGroups(Integer id);
    public Group getGroupById(Integer id);
    public Group insertGroup(Group group);
    public Group updateGroup(Integer id, Group group);

    public void deleteGroup(Integer id);

}
