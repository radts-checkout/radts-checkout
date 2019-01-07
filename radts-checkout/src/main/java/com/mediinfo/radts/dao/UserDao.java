package com.mediinfo.radts.dao;

import com.mediinfo.radts.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String>{

}
