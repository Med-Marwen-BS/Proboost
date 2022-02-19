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
        if(question.getAnswer()!=null)
        {
            original.setAnswer(question.getAnswer());
        }
        if(question.getReponseA()!=null)
        {
            original.setReponseA(question.getReponseA());
        }
        if(question.getReponseB()!=null)
        {
            original.setReponseB(question.getReponseB());
        }
        if(question.getReponseC()!=null)
        {
            original.setReponseC(question.getReponseC());
        }
        questionRepo.save(original);
        return original;
    }

    public List<Question> getall()
    {
        return questionRepo.findAll();
    }
}
