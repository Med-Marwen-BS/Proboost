package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Entretien;
import com.proboost.proboostproject.Modules.QCM;
import com.proboost.proboostproject.Services.QCMService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<QCM> getall()
    {
        return qcmService.getall();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id)
    {
        return qcmService.delete(id);
    }

    @GetMapping("/getbycreator/{id}")
    public List<QCM> getbycreator(@PathVariable("id") int id)
    {
        return qcmService.getbycreator(id);
    }

    @GetMapping("/getnumberquestions/{id}")
    public Integer getnumberquestions(@PathVariable("id")int id)
    {
        return qcmService.getnumberquestions(id);
    }

    @GetMapping("/addentretien/{id}/{entretien_id}")
    public QCM addentretien(@PathVariable("id")int id, @PathVariable("entretien_id") int entretien_id)
    {
        return qcmService.addentretien(id, entretien_id);
    }
}
