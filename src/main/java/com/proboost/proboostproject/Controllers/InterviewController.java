package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Interview;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Services.InterviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/videochat")
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

}
