package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Offre_Emploi;
import com.proboost.proboostproject.Respositories.OffreRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OffreService {

    private OffreRepo offreRepo;

    public Offre_Emploi add(Offre_Emploi offreEmploi)
    {
        offreEmploi.setPostedDate(LocalDateTime.now());
        return offreRepo.save(offreEmploi);
    }

    public List<Offre_Emploi> getall()
    {
        return offreRepo.findAll();
    }

    public Offre_Emploi getOffre( int id) {
        return offreRepo.findById(id).get();
    }

    public String delete(int id)
    {

        offreRepo.delete(offreRepo.findById(id).get());
        return "Offre Supprimée";
    }

 /*   public  Offre_Emploi update(Offre_Emploi newoffre )
    {
        return offreRepo.save(newoffre);
    }*/


    public Offre_Emploi update(Offre_Emploi offreEmploi)
    {
//        offreEmploi.setPostedDate(LocalDate.now());
        return offreRepo.save(offreEmploi);
    }


}
