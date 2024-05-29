package org.nstut.luvit.gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Byte> {
    Optional<Gender> findByName(EGender name);
}
