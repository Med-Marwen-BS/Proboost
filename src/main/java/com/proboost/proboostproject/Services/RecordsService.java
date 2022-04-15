package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Records;
import com.proboost.proboostproject.Respositories.RecordsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordsService {

    private RecordsRepo recordsRepo;

    public Records save(Records records)
    {
        return recordsRepo.save(records);
    }


    public Boolean findrecord(String user,int id)
    {
        return recordsRepo.findAll().stream().anyMatch(r -> {
            return (r.getUser().equals(user) && r.getQcm().getId() == id);
        });
    }

    public List<Records> getbyinterview(int id)
    {
        return recordsRepo.findAll().stream().filter(r -> r.getEntretien().getId()==id).collect(Collectors.toList());
    }
}
