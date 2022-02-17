package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Candidature;
import com.proboost.proboostproject.Respositories.CandidatureRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@AllArgsConstructor
@RequestMapping("/candidature")
public class CandidatureService {

    private CandidatureRepo candidatureRepo;

    public Candidature add(Candidature candidature)
    {
        return candidatureRepo.save(candidature);
    }

    public String delete(int id)
    {
        candidatureRepo.delete(candidatureRepo.findById(id).get());
        return "Candidature Supprimée";
    }

}
