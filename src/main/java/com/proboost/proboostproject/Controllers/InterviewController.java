package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Interview;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Services.InterviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/videochat")
@Slf4j
public class InterviewController {

    private InterviewService interviewService;

    @GetMapping("/getall/{userid}")
    public List<Interview> getall(@PathVariable("userid")int id)
    {
        return interviewService.getrequest(id);
    }

    @GetMapping("/getmyinterviews/{userid}")
    public List<Interview> getmyinterviews(@PathVariable("userid")int id)
    {
        return interviewService.getinterview(id);
    }

    @PostMapping("/save")
    public Interview save(@RequestBody Interview interview)
    {
        return interviewService.save(interview);
    }

    @GetMapping("/getuser/{username}")
    public User getuser(@PathVariable("username") String username)
    {
        return interviewService.getuser(username);
    }

    @GetMapping("/getcandidat/{id}")
    public User getcandidat(@PathVariable("id") int id)
    {
        return interviewService.getcandidat(id);
    }

    @GetMapping("/setcandidat/{id}/{candidat_id}")
    public void setcandidat(@PathVariable("id") int id,@PathVariable("candidat_id") int candidat_id)
    {
        interviewService.savecandidat(id,candidat_id);
    }

    @GetMapping("/finishchat/{id}")
    public void finishchat(@PathVariable("id") int id)
    {
        interviewService.finishchat(id);
    }
}
