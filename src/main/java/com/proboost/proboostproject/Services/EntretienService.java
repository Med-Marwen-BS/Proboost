package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Entretien;
import com.proboost.proboostproject.Respositories.EntretienRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntretienService {

    private EntretienRepo entretienRepo;

    public Entretien add(Entretien entretien)
    {
        return entretienRepo.save(entretien);
    }

    public Entretien findbyid(int id)
    {
        return entretienRepo.findById(id).get();
    }


    public String delete(int id)
    {
        entretienRepo.delete(entretienRepo.findById(id).get());
        return "Entretien Supprimée";
    }

    public Entretien update(Entretien entretien, int id)
    {
        Entretien original=entretienRepo.findById(id).get();
        if(entretien.getDate()!=null)
        {
            original.setDate(entretien.getDate());
        }
        if(entretien.getTitre()!=null)
        {
            original.setTitre(entretien.getTitre());
        }
        if(entretien.getType()!=null)
        {
            original.setType(entretien.getType());
        }
        if(entretien.getQcm()!=null)
        {
            original.setQcm(entretien.getQcm());
        }
        entretienRepo.save(original);
        return original;
    }
}
