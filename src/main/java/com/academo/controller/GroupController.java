package com.academo.controller;

import com.academo.DTO.Group.GroupDTO;
import com.academo.service.group.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupServiceImpl groupService;

    @GetMapping(value = "/groups/{id}")
    public List<GroupDTO> findByUserId(@PathVariable Integer id){
        return groupService.findByUserId(id);
    }
}
