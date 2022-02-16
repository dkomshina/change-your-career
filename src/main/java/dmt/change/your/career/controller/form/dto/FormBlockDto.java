package dmt.change.your.career.controller.form.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormBlockDto {

    private Long id;
    private String name;
    private List<FormSectionDto> sections;

}
