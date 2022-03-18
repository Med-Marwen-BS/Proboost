package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Question;
import com.proboost.proboostproject.Services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    public QuestionService questionService;

    @PostMapping("/add")
    public Question add(@RequestBody Question question)
    {
        return questionService.add(question);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        return questionService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Question update(@PathVariable("id")int id,@RequestBody Question question)
    {
        return questionService.update(question,id);
    }

    @GetMapping("/getall")
    public List<Question> getall()
    {
        return questionService.getall();
    }

    @GetMapping("/getbyqcm/{id}")
    public List<Question> getbyqcm(@PathVariable("id") int id)
    {
        return questionService.getByqcm(id);
    }
}
