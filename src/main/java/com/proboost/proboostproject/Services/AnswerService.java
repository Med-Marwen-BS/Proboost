package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Answer;
import com.proboost.proboostproject.Respositories.AnswerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerService {
    private AnswerRepo answerRepo;

    public Answer save(Answer entity)
    {
        return answerRepo.save(entity);
    }

    public String delete(int id)
    {
         answerRepo.deleteById(id);
         return "Answer Deleted";
    }

    public Answer get(int id)
    {
        return answerRepo.findById(id).get();
    }
}
