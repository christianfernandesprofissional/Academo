package com.academo.controller;

import com.academo.controller.dtos.activityType.ActivityTypeDTO;
import com.academo.controller.dtos.activityType.ActivityTypePostDTO;
import com.academo.model.Activity;
import com.academo.model.ActivityType;
import com.academo.security.authuser.AuthUser;
import com.academo.service.activityType.ActivityTypeServiceImpl;
import com.academo.service.user.UserServiceImpl;
import com.academo.util.exceptions.activityType.ActivityTypeNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity-types")
@Tag(name = "Tipos de Atividade")
public class ActivityTypeController {

    @Autowired
    ActivityTypeServiceImpl activityTypeService;
    @Autowired
    UserServiceImpl userServiceImp;

    @Operation(summary = "Recupera a lista de todos os tipos de atividade de um usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de Atividade recuperados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar recuperar tipos de atividade"),
            @ApiResponse(responseCode = "404", description = "Nenhum tipo de atividade encontrada")
    })
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

    @Operation(summary = "Recupera um tipo de atividade de um usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de Atividade recuperado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar recuperar tipo de atividade"),
            @ApiResponse(responseCode = "404", description = "Nenhum tipo de atividade encontrado com este ID")
    })
    @GetMapping
    public ResponseEntity<ActivityTypeDTO> getActivity(Authentication authentication, @RequestParam Integer id) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType activityType = activityTypeService.findByIdAndUserId(id, userId);
        ActivityTypeDTO activityTypeDTO = new ActivityTypeDTO(activityType.getId(), activityType.getName(), activityType.getDescription());
        return ResponseEntity.ok(activityTypeDTO);
    }

    @Operation(summary = "Cadastra um tipo de atividade", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de atividade cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar cadastrar tipo de atividade"),
    })
    @PostMapping
    public ResponseEntity<ActivityType> createActivity(Authentication authentication, @RequestBody ActivityTypePostDTO activityTypeDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        activityTypeService.create(userId, new ActivityType(activityTypeDTO.name(), activityTypeDTO.description()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualiza um tipo de atividade", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atividade atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar atualizar tipo de atividade"),
            @ApiResponse(responseCode = "404", description = "Nenhum tipo de atividade encontrado com este ID")
    })
    @PutMapping
    public ResponseEntity<Activity> updateActivity(Authentication authentication, @RequestBody ActivityTypeDTO activityTypeDTO) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        ActivityType activityType = new ActivityType(activityTypeDTO.name(), activityTypeDTO.description());
        activityType.setId(activityTypeDTO.id());
        activityTypeService.update(userId, activityType);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove um tipo de atividade", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de atividade deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar deletar tipo de atividade"),
            @ApiResponse(responseCode = "404", description = "Nenhum tipo de atividade encontrado com este ID")
    })
    @DeleteMapping
    public ResponseEntity<ActivityType> deleteActivityType(Authentication authentication, @RequestParam Integer activityTypeId) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        activityTypeService.deleteActivityType(userId, activityTypeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
