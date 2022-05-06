package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Alerts;
import com.proboost.proboostproject.Services.AlertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/getall/{id}")
    public List<Alerts> getall(@PathVariable("id") int id)
    {
        return alertService.getall(id);
    }

    @GetMapping("/open/{type}")
    public void openall(@PathVariable("type") String type)
    {
        alertService.openall(type);
    }
}
