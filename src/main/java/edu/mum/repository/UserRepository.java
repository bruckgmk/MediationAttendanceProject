package edu.mum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}