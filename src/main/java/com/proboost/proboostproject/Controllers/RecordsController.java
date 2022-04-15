package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Records;
import com.proboost.proboostproject.Services.RecordsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findrecord/{user}/{id}")
    public Boolean findrecord(@PathVariable("user") String user,@PathVariable("id")int id)
    {
        return recordsService.findrecord(user,id);
    }

    @GetMapping("/getbyinterview/{id}")
    public List<Records> getbyinterview(@PathVariable("id")int id)
    {
        return recordsService.getbyinterview(id);
    }
}
