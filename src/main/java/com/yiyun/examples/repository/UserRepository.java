package com.yiyun.examples.repository;

import com.yiyun.examples.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    public List<User> findAll();
    public List<User> findByNick(String nick);
    public User findByUsername(String username);
}
