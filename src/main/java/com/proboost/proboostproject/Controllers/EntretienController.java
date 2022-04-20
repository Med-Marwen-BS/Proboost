package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Cheaters;
import com.proboost.proboostproject.Modules.Entretien;
import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Services.EntretienService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/entretien")
public class EntretienController {

    private EntretienService entretienService;

    @PostMapping("/save")
    public Entretien save(@RequestBody Entretien entretien)
    {
        return entretienService.add(entretien);
    }

    @GetMapping("/get/{id}")
    public Entretien get(@PathVariable("id") int id)
    {
        return entretienService.findbyid(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        return entretienService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Entretien update(@RequestBody Entretien entretien,@PathVariable("id") int id)
    {
        return entretienService.update(entretien,id);
    }

    @GetMapping("/getbyrecruter/{id}")
    public List<Entretien> getbyrecruter(@PathVariable("id")int id)
    {
        return entretienService.getbyrecruter(id);
    }

    @GetMapping("/getquiznumber/{id}")
    public int getquiznumber(@PathVariable("id")int id)
    {
        return entretienService.quiznumber(id);
    }

    @GetMapping("/getqcmbyentretien/{id}")
    public List<QCM> getqcmbyentretien(@PathVariable("id") int id)
    {
        return entretienService.getqcmbyentretien(id);
    }

    @DeleteMapping("/deleteqcm/{entretien_id}/{qcm_id}")
    public void deleteqcm(@PathVariable("entretien_id")int entretien_id,@PathVariable("qcm_id")int qcm_id)
    {
        entretienService.deleteqcm(entretien_id,qcm_id);
    }

    @GetMapping("/getbycandidat/{id}")
    public List<Entretien> getbycandidat(@PathVariable("id")int id)
    {
        return entretienService.getbycandidat(id);
    }

    @GetMapping("/getcheaters/{id}")
    public List<Cheaters> getcheaters(@PathVariable("id") int id)
    {
        return entretienService.getcheaters(id);
    }

    @GetMapping("/verifycheaters/{id}")
    public Boolean verifycheaters(@PathVariable("id")int id)
    {
        return entretienService.verifycheaters(id);
    }
}
