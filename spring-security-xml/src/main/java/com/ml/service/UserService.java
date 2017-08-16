package com.ml.service;

import com.ml.models.Resource;
import com.ml.models.Role;
import com.ml.models.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    public UserInfo getUserByName(String username) {
        return new UserInfo(username, "$2a$10$oNkst6opzbSMl8RMXDaZg.hPf/X2tQDfNTyy0vvX9yoSJ.gPG7XEe");
    }

    public List<Resource> findAllResource() {
        List<Resource> resources = new ArrayList<>();
        resources.add(new Resource("1", "/info"));
        resources.add(new Resource("2", "/admin"));
        return resources;
    }

    public List<Role> findResourceNeedRole(Resource resource) {
        List<Role> roles = new ArrayList<>();
        Role user = new Role("1", "USER");
        Role admin = new Role("2", "ADMIN");

        roles.add(admin);
        if (resource.getId().equals("1"))
            roles.add(user);
        return roles;
    }
}
