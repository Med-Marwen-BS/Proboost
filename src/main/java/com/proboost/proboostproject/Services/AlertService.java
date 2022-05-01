package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Alerts;
import com.proboost.proboostproject.Respositories.AlertRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertService {

    private AlertRepo alertRepo;

    public Alerts save(Alerts alerts)
    {
        return alertRepo.save(alerts);
    }
}
