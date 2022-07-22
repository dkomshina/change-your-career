package dmt.change.your.career.service.changeller;

import javax.persistence.EntityNotFoundException;

import dmt.change.your.career.service.changeller.entity.Changeller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangellerRepository extends JpaRepository<Changeller, Long> {

    default Changeller findByIdOrThrow(long id) {
        return findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
