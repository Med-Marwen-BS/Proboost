package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Offre_Emploi;
import com.proboost.proboostproject.Respositories.OffreRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OffreService {

    private OffreRepo offreRepo;

    public Offre_Emploi add(Offre_Emploi offreEmploi)
    {
        return offreRepo.save(offreEmploi);
    }

    public List<Offre_Emploi> getall()
    {
        return offreRepo.findAll();
    }

    public String delete(int id)
    {

        offreRepo.delete(offreRepo.findById(id).get());
        return "Offre Supprimée";
    }
}
