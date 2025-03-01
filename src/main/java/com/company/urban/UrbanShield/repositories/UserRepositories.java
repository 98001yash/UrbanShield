package com.company.urban.UrbanShield.repositories;


import com.company.urban.UrbanShield.dto.UserDto;
import com.company.urban.UrbanShield.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepositories extends JpaRepository<User,Long> {


}
