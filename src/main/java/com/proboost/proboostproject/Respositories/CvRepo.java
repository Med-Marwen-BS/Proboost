package com.proboost.proboostproject.Respositories;

import com.proboost.proboostproject.Modules.cvFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvRepo extends JpaRepository<cvFile,String> {
}
