package dmt.change.your.career.controller.form;

import dmt.change.your.career.controller.form.dto.FormDto;
import dmt.change.your.career.service.form.FormQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/dmt"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class FormController {

    private final FormQueryService formQueryService;

    @GetMapping(value = "/form")
    public FormDto getForm() throws Exception {
        return formQueryService.getForm();
    }
}
