package com.infymemobile.repository;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infymemobile.entity.DigitalBankAccountEntity;
import com.infymemobile.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByMobileNumber(Long mobileNumber);

    Optional<User> findByUserId(String userId);

    User findFirstByOrderByUserIdDesc();
}
