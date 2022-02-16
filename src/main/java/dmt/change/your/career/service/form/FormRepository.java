package dmt.change.your.career.service.form;

import java.util.List;

import dmt.change.your.career.service.form.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {

    List<Form> findByOutdatedIsFalse();

    default Form findNotOutdatedForm() throws Exception {
        var forms = findByOutdatedIsFalse();
        if (forms.size() != 1) {
            throw new Exception("Несколько форм считаются актуальными");
        }
        return forms.get(0);
    }
}