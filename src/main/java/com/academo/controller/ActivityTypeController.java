package com.academo.controller;

import com.academo.controller.dtos.activityType.ActivityTypeDTO;
import com.academo.controller.dtos.activityType.ActivityTypePostDTO;
import com.academo.model.Activity;
import com.academo.model.ActivityType;
import com.academo.security.authuser.AuthUser;
import com.academo.service.activityType.ActivityTypeServiceImpl;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity-types")
public class ActivityTypeController {

    @Autowired
    ActivityTypeServiceImpl activityTypeService;
    @Autowired
    UserServiceImpl userServiceImp;

    // Encontra todos os registros
    @GetMapping("/all")
    public ResponseEntity<List<ActivityTypeDTO>> getActivities(Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        List<ActivityTypeDTO> types  = activityTypeService.findAll(userId)
                .stream()
                .map(t -> new ActivityTypeDTO(
                        t.getId(),
                        t.getName(),
                        t.getDescription()
                )).toList();
        return ResponseEntity.ok(types);
    }

    // Encontra registro específico
    @GetMapping
    public ResponseEntity<ActivityTypeDTO> getActivity(Authentication authentication, @RequestParam Integer id) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType activityType = activityTypeService.findByIdAndUserId(id, userId);
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO(activityType.getId(), activityType.getName(), activityType.getDescription());
        return ResponseEntity.ok(activityTypeDTO);
    }

    // Criação
    @PostMapping
    public ResponseEntity<ActivityType> createActivity(Authentication authentication, @RequestBody ActivityTypePostDTO activityTypeDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        activityTypeService.create(userId, new ActivityType(activityTypeDTO.name(), activityTypeDTO.description()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Atualização
    @PutMapping
    public ResponseEntity<Activity> updateActivity(Authentication authentication, @RequestBody ActivityTypeDTO activityTypeDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType activityType = new ActivityType(activityTypeDTO.name(), activityTypeDTO.description());
        activityType.setId(activityTypeDTO.id());
        activityTypeService.update(userId, activityType);
        return ResponseEntity.ok().build();
    }

    // Deleção
    @DeleteMapping
    public ResponseEntity<ActivityType> deleteActivityType(Authentication authentication, @RequestParam Integer activityTypeId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        activityTypeService.deleteActivityType(userId, activityTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
