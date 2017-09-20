package com.ml.service;

import com.ml.models.User;
import com.ml.repository.UserRepository;
import com.ml.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Cacheable(cacheNames = "users", key = "#id+'_findById'")
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public User save(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return userRepository.save(user);
    }

    @CacheEvict(cacheNames = "users", key = "#userVo.id+'_findById'")
    public User update(UserVo userVo) {
        User user = userRepository.findOne(userVo.getId());
        user.setName(userVo.getName());
        user.setAge(userVo.getAge());
        return userRepository.save(user);
    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    public void delete(Long id) {
        User one = userRepository.findOne(id);
        if (one != null)
            userRepository.delete(one);
    }

    public User findById1(Long id) {
        return findById(id);
    }
}