package org.nstut.luvit.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;

    @Autowired
    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(ERole eRole) {
        return roleRepository.findByName(eRole)
                .orElseThrow(IllegalArgumentException::new);
    }
}
