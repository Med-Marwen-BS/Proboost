package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepo extends JpaRepository<Interview,Integer> {
}
