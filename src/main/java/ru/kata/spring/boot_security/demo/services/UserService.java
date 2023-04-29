package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService extends UserDetailsService {

    void create(User user);

    User read(Long id);

    void update(User user);

    void delete(Long id);

    List<User> listUser();

}
