package dmt.change.your.career.service.suggest;

import java.util.List;

import dmt.change.your.career.service.suggest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = "" +
            "select * " +
            "from course " +
            "where id in (:ids) "
    )
    List<Course> findAllCoursesByIds(List<Long> ids);

    List<Course> findAllByMlSendIsFalse();

    @Modifying
    @Query(nativeQuery = true, value = "" +
            "update course " +
            "set ml_send = true " +
            "where id = :id "
    )
    void setCourseMlSendTrue(long id);
}
