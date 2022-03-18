package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Records;
import com.proboost.proboostproject.Services.RecordsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/records")
public class RecordsController {

    private RecordsService recordsService;

    @PostMapping("/save")
    public Records save(@RequestBody Records records)
    {
        return recordsService.save(records);
    }
}
