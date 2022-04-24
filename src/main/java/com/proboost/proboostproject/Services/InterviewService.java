package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Interview;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Respositories.InterviewRepo;
import com.proboost.proboostproject.Respositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InterviewService {

    private InterviewRepo interviewRepo;
    private UserRepo userRepo;

    public List<Interview> getrequest(Integer userid)
    {
        return interviewRepo.findAll().stream().filter(i -> i.getCandidat().getId()==userid).collect(Collectors.toList());
    }

    public List<Interview> getinterview(Integer userid)
    {
        return interviewRepo.findAll().stream().filter(i -> i.getRecruter().getId()==userid).collect(Collectors.toList());
    }

    public Interview save(Interview interview)
    {
        return interviewRepo.save(interview);
    }

    public User getuser(String username)
    {
        return userRepo.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst().get();
    }
}
