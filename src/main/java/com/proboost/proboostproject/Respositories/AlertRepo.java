package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<Alerts,Integer> {
}
