package com.proboost.proboostproject.Services;

import com.proboost.proboostproject.Modules.Cheaters;
import com.proboost.proboostproject.Respositories.CheatersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheatersService {

    private CheatersRepo cheatersRepo;

    public Cheaters save(Cheaters cheaters)
    {
        return cheatersRepo.save(cheaters);
    }
}
