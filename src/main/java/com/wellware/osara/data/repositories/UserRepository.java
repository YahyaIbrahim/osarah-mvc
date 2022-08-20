package com.wellware.osara.data.repositories;

import com.wellware.osara.data.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

}
