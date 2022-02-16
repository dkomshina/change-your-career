package dmt.change.your.career.service.form;

import java.util.List;

import dmt.change.your.career.service.form.entity.FormElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormElementRepository extends JpaRepository<FormElement, Long> {

    List<FormElement> findAllBySectionIdAndOutdatedIsFalse(Long sectionId);
}
