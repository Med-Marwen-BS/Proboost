package com.proboost.proboostproject.Controllers;

import com.proboost.proboostproject.Modules.Cheaters;
import com.proboost.proboostproject.Services.CheatersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cheaters")
public class CheatersController {

    private CheatersService cheatersService;

    @PostMapping("/save")
    public Cheaters save(@RequestBody Cheaters cheaters)
    {
       return  cheatersService.save(cheaters);
    }

}
