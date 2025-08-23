package com.academo.service.group;

import com.academo.model.Group;
import com.academo.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findAll() {
        return List.of();
    }

    @Override
    public Group findById(Integer id) {
        return null;
    }

    @Override
    public Group create(Group group) {
        return null;
    }

    @Override
    public Group update(Group group) {
        return null;
    }
}
