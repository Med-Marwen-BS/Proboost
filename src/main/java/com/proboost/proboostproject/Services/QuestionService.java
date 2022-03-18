package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Question;
import com.proboost.proboostproject.Respositories.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {


    private QuestionRepo questionRepo;

    public Question add(Question question)
    {
        return questionRepo.save(question);
    }

    public String delete(int id)
    {
        questionRepo.delete(questionRepo.findById(id).get());
        return "Question Supprimé";
    }

    public Question update(Question question,int id)
    {
        Question original=questionRepo.findById(id).get();
        if(question.getQuestion()!=null)
        {
            question.setQuestion(question.getQuestion());
        }
        if(question.getCorrectanswer()!=null)
        {
            original.setCorrectanswer(question.getCorrectanswer());
        }

        questionRepo.save(original);
        return original;
    }

    public List<Question> getall()
    {
        return questionRepo.findAll();
    }

    public List<Question> getByqcm(int id)
    {
        return questionRepo.findAll().stream().filter(q -> q.getQcm().getId()==id).collect(Collectors.toList());
    }
}
