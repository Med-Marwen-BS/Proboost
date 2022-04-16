package com.proboost.proboostproject.Controllers;


import com.proboost.proboostproject.Modules.Offre_Emploi;
import com.proboost.proboostproject.Services.OffreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
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
    public ResponseEntity< List<Offre_Emploi>> getall()
    {
        System.out.println("it works offre");
        return  new ResponseEntity<List<Offre_Emploi>>(offreService.getall(), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id)
    {
        return offreService.delete(id);
    }

}
