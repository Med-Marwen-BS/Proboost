package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Offre_Emploi;
import com.proboost.proboostproject.Modules.User;
import com.proboost.proboostproject.Modules.cvFile;
import com.proboost.proboostproject.Respositories.OffreRepo;
import com.proboost.proboostproject.Respositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OffreService {

    private  OffreRepo offreRepo;

    private UserRepo userRepo;



    public Offre_Emploi add(Offre_Emploi offreEmploi , int id)
    {
        User currentuser=userRepo.findById(id).orElse(null);
        offreEmploi.setRecruteur(currentuser);

        offreEmploi.setPostedDate(LocalDate.now());
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




    public Offre_Emploi update(Offre_Emploi offreEmploi)
    {
        return offreRepo.save(offreEmploi);
    }

    public List<cvFile> getOfferCVs(int id) {
        return offreRepo.findById(id).get().getCvFiles();   }

    public int maxSalary(){
        int max=0;
        List<Offre_Emploi> offers =  offreRepo.findAll();
        for(Offre_Emploi off:offers){
            if(off.getSalary()>max)
                max=off.getSalary();
        }

        return max;
    }


    public int minSalary(){

        List<Offre_Emploi> offers =  offreRepo.findAll();
        int min=maxSalary();
        for(Offre_Emploi off:offers){
            if(off.getSalary()<min)
                min=off.getSalary();
        }

        return min;
    }

    public List<Offre_Emploi> offreSalaryRange( int min , int max){
        List<Offre_Emploi> offers =  offreRepo.findAll();
        List<Offre_Emploi> result=  offers.stream().filter(off-> off.getSalary()>=min && off.getSalary() <= max).collect(Collectors.toList());

        return result;
    }
}