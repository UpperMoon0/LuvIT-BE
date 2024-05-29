package org.nstut.luvit.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final IStatusRepository statusRepository;

    @Autowired
    public StatusService(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getStatus(EStatus eStatus) {
        return statusRepository.findByName(eStatus)
                .orElseThrow(IllegalArgumentException::new);
    }
}
