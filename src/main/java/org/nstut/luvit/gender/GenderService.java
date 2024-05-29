package org.nstut.luvit.gender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    private final IGenderRepository genderRepository;

    @Autowired
    public GenderService (IGenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public Gender getGender(EGender eGender) {
        return genderRepository.findByName(eGender)
                .orElseThrow(IllegalArgumentException::new);
    }
}
