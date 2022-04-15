package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Entretien;
import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Respositories.EntretienRepo;
import com.proboost.proboostproject.Respositories.QCMRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntretienService {

    private EntretienRepo entretienRepo;
    private QCMRepo qcmRepo;

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
        Entretien entretien = entretienRepo.findById(id).get();
        entretien.setQcm(null);
        entretienRepo.save(entretien);
        qcmRepo.findAll().stream().filter(q -> q.getEntretiens().contains(entretien)).map(q -> {
                    q.getEntretiens().remove(entretien);return qcmRepo.save(q);
                })
                        .collect(Collectors.toList());
        entretienRepo.delete(entretien);
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

    public List<Entretien> getbyrecruter(int id)
    {
        return entretienRepo.findAll().stream().filter(e -> e.getRecruteur().getId()==id).collect(Collectors.toList());
    }

    public int quiznumber(int id)
    {
        return entretienRepo.findById(id).get().getQcm().size();
    }

    public List<QCM> getqcmbyentretien(int id)
    {
        return qcmRepo.findAll().stream().filter(q -> q.getEntretiens().stream().anyMatch(e -> e.getId()==id)).collect(Collectors.toList());
    }

    public void deleteqcm(int entretien_id,int qcm_id)
    {
        Entretien entretien=entretienRepo.findById(entretien_id).get();
        QCM qcm=qcmRepo.findById(qcm_id).get();
        List<QCM> updatedqcmlist=entretien.getQcm().stream()
                .filter(q -> q.getId()!=qcm_id).collect(Collectors.toList());
        entretien.setQcm(updatedqcmlist);
        List<Entretien> updatedentretienlist=qcm.getEntretiens().stream()
                        .filter(e -> e.getId()!=entretien_id).collect(Collectors.toList());
        qcm.setEntretiens(updatedentretienlist);
        entretienRepo.save(entretien);
        qcmRepo.save(qcm);
    }

    public List<Entretien> getbycandidat(int id)
    {
        return entretienRepo.findAll().stream().filter(e -> e.getCandidat().getId()==id).collect(Collectors.toList());
    }
}
