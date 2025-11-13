package com.academo.controller;


import com.academo.controller.dtos.profile.ProfilePutDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.academo.model.Profile;
import com.academo.security.authuser.AuthUser;
import com.academo.service.profile.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
@Tag(name = "Perfil")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl service;


    @Operation(summary = "Recupera o perfil do usuário", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perfil recuperado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao tentar recuperar perfil"),
        @ApiResponse(responseCode = "404", description = "Perfil não encontrado")
    })
    @GetMapping
    public ResponseEntity<Profile> getProfile(Authentication authentication) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Profile profile = service.findById(userId);
        profile.setUsageStorage(((AuthUser) authentication.getPrincipal()).getUser().getStorageUsage());
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Atualiza o perfil do usuário", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao tentar atualizar perfil")
    })
    @PutMapping
    public ResponseEntity<Profile> updateProfile(Authentication authentication, @RequestBody ProfilePutDTO profileDto) {
        Integer userId = ((AuthUser) authentication.getPrincipal()).getUser().getId();
        Profile profile = new  Profile(profileDto);
        Profile savedProfile = service.update(userId, profile);
        return ResponseEntity.ok(savedProfile);
    }
}
