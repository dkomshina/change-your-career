package dmt.change.your.career.service.form;

import java.util.List;

import dmt.change.your.career.service.form.entity.FormElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FormElementRepository extends JpaRepository<FormElement, Long> {

    List<FormElement> findAllBySectionIdAndOutdatedIsFalse(Long sectionId);

    @Query(nativeQuery = true, value = "" +
            "select s.ml_name || '_' || e.ml_name " +
            "from form_element e " +
            "         left join form_section s on s.id = e.section_id " +
            "where e.id = :id "
    )
    String getMlName(long id);

    @Query(nativeQuery = true, value = "" +
            "select s.ml_name || '_number' " +
            "from form_element e " +
            "         left join form_section s on s.id = e.section_id " +
            "where e.id = :id "
    )
    String getMlNumberName(long id);
}
