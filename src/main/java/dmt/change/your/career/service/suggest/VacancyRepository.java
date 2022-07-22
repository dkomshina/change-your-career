package dmt.change.your.career.service.suggest;

import java.util.List;

import dmt.change.your.career.service.suggest.entity.Event;
import dmt.change.your.career.service.suggest.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(nativeQuery = true, value = "" +
            "select * " +
            "from vacancy " +
            "where id in (:ids) "
    )
    List<Vacancy> findAllVacanciesByIds(List<Long> ids);

    List<Vacancy> findAllByMlSendIsFalse();

    @Modifying
    @Query(nativeQuery = true, value = "" +
            "update vacancy " +
            "set ml_send = true " +
            "where id = :id "
    )
    void setVacancyMlSendTrue(long id);
}
