package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired private RoleRepository repo;

    @Test
    public void testCreateRoles(){
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        repo.saveAll(List.of(user, admin));
        List<Role> listRoles = repo.findAll();
        assert listRoles.size() == 2;
    }
}
