package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Services.QCMService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/qcm")
public class QCMController {

    private QCMService qcmService;

    @PostMapping("/add")
    public QCM save(@RequestBody QCM qcm)
    {
        return qcmService.save(qcm);
    }

    @PutMapping("/update/{id}")
    public QCM update(@RequestBody QCM qcm,@PathVariable("id") int id)
    {
        return qcmService.update(qcm,id);
    }

    @GetMapping("/get/{id}")
    public QCM get(@PathVariable("id") int id)
    {
        return qcmService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id)
    {
        return qcmService.delete(id);
    }
}
