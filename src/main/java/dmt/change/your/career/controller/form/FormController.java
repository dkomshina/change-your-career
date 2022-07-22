package dmt.change.your.career.controller.form;

import java.util.List;

import dmt.change.your.career.controller.form.dto.FormAnswerDto;
import dmt.change.your.career.controller.form.dto.FormDto;
import dmt.change.your.career.ml.MlClient;
import dmt.change.your.career.service.form.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/dmt/form"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FormController {

    private final FormService formService;
    private final MlClient mlClient;

    @GetMapping
    public FormDto getForm() throws Exception {
        return formService.getForm();
    }

    @PostMapping
    public void submitForm(
            @RequestParam("sessionId") long sessionId,
            @RequestBody List<FormAnswerDto<?>> formAnswerDtos
    ) {
        var changeller = formService.submitForm(sessionId, formAnswerDtos);
        try {
            mlClient.createMlUser(changeller);
        } catch (Exception e) {

        }
    }
}
