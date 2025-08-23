package com.academo.service.profile;

import com.academo.model.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProfileService  {

    public List<Profile> findAll();
    public Profile findById(Integer id);
    public Profile create(Profile profile);
    public Profile update(Profile profile);
}
