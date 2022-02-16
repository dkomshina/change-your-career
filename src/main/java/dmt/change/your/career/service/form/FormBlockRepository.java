package dmt.change.your.career.service.form;

import java.util.List;

import dmt.change.your.career.service.form.entity.FormBlock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormBlockRepository extends JpaRepository<FormBlock, Long> {

    List<FormBlock> findAllByFormIdAndOutdatedIsFalse(Long formId);
}
