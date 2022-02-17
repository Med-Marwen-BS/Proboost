package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.Offre_Emploi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepo extends JpaRepository<Offre_Emploi,Integer> {

}
