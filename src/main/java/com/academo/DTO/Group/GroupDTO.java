package com.academo.DTO.Group;

import com.academo.model.Group;
import com.academo.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

public class GroupDTO {

    // Atributos
    private int id;
    private String name;
    private String description;
    private User user;
    private boolean isActive;

    // Construtores
    public GroupDTO() {
    }

    public GroupDTO(Group group) {
        this.setId(group.getId());
        this.setName(group.getName());
        this.setDescription(group.getDescription());
        this.setUser(group.getUser());
        this.setActive(group.isActive());
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return id == groupDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
