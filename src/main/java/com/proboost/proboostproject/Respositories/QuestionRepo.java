package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Integer> {
}
