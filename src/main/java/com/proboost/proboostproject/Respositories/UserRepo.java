package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
