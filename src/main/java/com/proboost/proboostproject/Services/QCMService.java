package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Entretien;
import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Modules.Question;
import com.proboost.proboostproject.Respositories.EntretienRepo;
import com.proboost.proboostproject.Respositories.QCMRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QCMService {

    private QCMRepo qcmRepo;

    private EntretienRepo entretienRepo;

    public QCM save(QCM qcm)
    {
        return qcmRepo.save(qcm);
    }

    public QCM update(QCM qcm,int id)
    {
        QCM original=qcmRepo.findById(id).get();
        if(qcm.getTitre()!=null)
        {
            original.setTitre(qcm.getTitre());
        }
        if(qcm.getTemp()!=0)
        {
            original.setTemp(qcm.getTemp());
        }
        qcmRepo.save(original);
        return original;
    }

    public QCM get(int id)
    {
        return qcmRepo.findById(id).get();
    }

    public String delete(int id)
    {
        qcmRepo.delete(qcmRepo.findById(id).get());
        return "QCM Supprimée";
    }

    public List<QCM> getall()
    {
        return qcmRepo.findAll();
    }

    public List<QCM> getbycreator(int id)
    {
        return qcmRepo.findAll().stream().filter(c -> c.getCreateur().getId()==id).collect(Collectors.toList());
    }

    public int getnumberquestions(int id)
    {
        return qcmRepo.findById(id).get().getQuestions().size();
    }

    public QCM addentretien(int id,int entretien_id)
    {
        QCM qcm=qcmRepo.findById(id).get();
        Entretien entretien=entretienRepo.findById(entretien_id).get();
        log.info(String.valueOf(entretien.getId()));
        qcm.getEntretiens().add(entretien);
        entretien.getQcm().add(qcm);
        entretienRepo.save(entretien);
        return qcmRepo.save(qcm);
    }
}
