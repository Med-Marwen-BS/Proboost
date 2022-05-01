package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Alerts;
import com.proboost.proboostproject.Services.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
@AllArgsConstructor
public class AlertController {

    private AlertService alertService;

    @PostMapping("/save")
    public Alerts save(@RequestBody Alerts alert)
    {
        return alertService.save(alert);
    }
}
