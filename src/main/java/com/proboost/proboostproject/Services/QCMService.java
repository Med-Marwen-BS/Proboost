package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Modules.Question;
import com.proboost.proboostproject.Respositories.QCMRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QCMService {

    private QCMRepo qcmRepo;

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

}
