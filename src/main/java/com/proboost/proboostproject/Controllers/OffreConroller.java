package com.proboost.proboostproject.Controllers;


import com.proboost.proboostproject.Modules.Offre_Emploi;
import com.proboost.proboostproject.Services.OffreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/offre")
public class OffreConroller {

    private OffreService offreService;

    @PostMapping("/add")
    public Offre_Emploi add(@RequestBody Offre_Emploi offreEmploi)
    {
        return offreService.add(offreEmploi);
    }

    @GetMapping("/all")
    public List<Offre_Emploi> getall()
    {
        return offreService.getall();
    }

    @GetMapping("/{id}")
    public Offre_Emploi get(@PathVariable("id") int id){
        return offreService.getOffre(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        return offreService.delete(id);
    }

//    public Offre_Emploi update(@RequestBody Offre_Emploi offreEmploi){return offreService.update(offreEmploi);}
    @PutMapping("")
   public Offre_Emploi update(@RequestBody Offre_Emploi offreEmploi)
   {
       return offreService.update(offreEmploi);
   }


}
