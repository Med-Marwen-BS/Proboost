package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Answer;
import com.proboost.proboostproject.Services.AnswerService;
import com.proboost.proboostproject.Services.QCMService;
import com.proboost.proboostproject.Services.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/answer")
public class AnswerController {

    private AnswerService answerService;
    private QuestionService questionService;

    @PostMapping("/save/{id}")
    public Answer save(@RequestBody Answer answer,@PathVariable("id")int id)
    {
        answer.setQuestion(questionService.getonequestion(id));
        return answerService.save(answer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  delete(@PathVariable("id")int id)
    {

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Access-Control-Allow-Origin","*");
        return new ResponseEntity<String>(answerService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Answer getone(@PathVariable("id")int id)
    {
        return answerService.get(id);
    }

}
