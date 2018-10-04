package com.repo;

import com.dom.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
    User findByMailAndPassword(String mail, String password);
    User findByUsername(String username);
    User findByMail(String mail);
    User findByUsernameOrMail(String username, String mail);
}
