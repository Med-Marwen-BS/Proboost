package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Candidature;
import com.proboost.proboostproject.Services.CandidatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/candidature")
public class CandidatureController {

    private CandidatureService candidatureService;

    @PostMapping("/add")
    public Candidature add(@RequestBody Candidature candidature)
    {
      return candidatureService.add(candidature);
    }



    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        return candidatureService.delete(id);
    }
}
