package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Alerts;
import com.proboost.proboostproject.Respositories.AlertRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlertService {

    private AlertRepo alertRepo;

    public Alerts save(Alerts alerts)
    {
        return alertRepo.save(alerts);
    }

    public List<Alerts> getall(int id)
    {
        return alertRepo.findAll().stream().filter(a -> a.getUser().getId()==id).collect(Collectors.toList());
    }


    public void openall(String type)
    {
        alertRepo.findAll().stream()
                .filter(a -> a.getType().equals(type))
                .map(a -> {alertRepo.deleteById(a.getId());return a;})
                .collect(Collectors.toList());
    }
}
