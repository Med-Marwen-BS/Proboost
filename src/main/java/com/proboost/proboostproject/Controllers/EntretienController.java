package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Entretien;
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

}
