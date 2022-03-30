package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Answer;
import com.proboost.proboostproject.Modules.Question;
import com.proboost.proboostproject.Respositories.AnswerRepo;
import com.proboost.proboostproject.Respositories.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionService {


    private QuestionRepo questionRepo;

    private AnswerRepo answerRepo;

    public Question add(Question question)
    {
        Answer answer=new Answer();
        answer.setText(question.getCorrectanswer());
        Question savedquestion= questionRepo.save(question);
        answer.setQuestion(savedquestion);
        answerRepo.save(answer);
        return savedquestion;
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

    public Question getonequestion(int id)
    {
        return questionRepo.findById(id).get();
    }

    public void updatecorrectanswer(int id,Answer answer)
    {
        Question question=questionRepo.findById(id).get();
        question.setCorrectanswer(answer.getText());
        questionRepo.save(question);
    }
}
