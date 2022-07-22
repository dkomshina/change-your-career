package dmt.change.your.career.service.suggest;

import java.util.List;

import dmt.change.your.career.service.suggest.entity.Article;
import dmt.change.your.career.service.suggest.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value = "" +
            "select * " +
            "from event " +
            "where id in (:ids) "
    )
    List<Event> findAllEventsByIds(List<Long> ids);

    List<Event> findAllByMlSendIsFalse();

    @Modifying
    @Query(nativeQuery = true, value = "" +
            "update event " +
            "set ml_send = true " +
            "where id = :id "
    )
    void setEventMlSendTrue(long id);
}
