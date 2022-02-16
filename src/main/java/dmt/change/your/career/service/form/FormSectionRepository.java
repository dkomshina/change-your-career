package dmt.change.your.career.service.form;

import java.util.List;

import dmt.change.your.career.service.form.entity.FormSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormSectionRepository extends JpaRepository<FormSection, Long> {

    List<FormSection> findAllByBlockIdAndOutdatedIsFalse(Long blockId);
}
