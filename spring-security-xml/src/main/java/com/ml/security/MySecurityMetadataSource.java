package com.ml.security;

import com.ml.models.Resource;
import com.ml.models.Role;
import com.ml.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 资源角色管理器
 */
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void loadResourceDefine() {
        log.debug(" ---------------  MySecurityMetadataSource loadResourceDefine--------------- ");
        if (resourceMap == null) {
            resourceMap = new HashMap<>();

            List<Resource> resources = userService.findAllResource();
            resources.stream().forEach(resource -> {
                List<Role> roles = userService.findResourceNeedRole(resource);
                List<ConfigAttribute> configAttributes = roles.stream().map(role -> new SecurityConfig(role.getName())).collect(Collectors.toList());
                resourceMap.put(new AntPathRequestMatcher(resource.getUrl()), configAttributes);
            });
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        log.debug("-----------MySecurityMetadataSource getAttributes ----------- ");
        if (resourceMap == null)
            loadResourceDefine();

        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
