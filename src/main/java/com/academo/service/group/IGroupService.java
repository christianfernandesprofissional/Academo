package com.academo.service.group;

import com.academo.model.Group;

import java.util.List;

public interface IGroupService{

    public List<Group> getGroups(Integer id);
    public Group getGroupByIdAndUserId(Integer userId, Integer id);
    public Group insertGroup(Integer userId, Group group);
    public Group updateGroup(Integer userId, Group group);
    public void deleteGroup(Integer userId, Integer groupId);
    public Group addSubjectToGroup(Integer userId, Integer groupId, Integer SubjectId);
    public Group deleteSubjectFromGroup(Integer userId, Integer groupId, Integer SubjectId);
    public Group associateSubjects(Integer userId, Integer groupId, List<Integer> subjectsIds);

}
