package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Records;
import com.proboost.proboostproject.Respositories.RecordsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecordsService {

    private RecordsRepo recordsRepo;

    public Records save(Records records)
    {
        return recordsRepo.save(records);
    }


}
