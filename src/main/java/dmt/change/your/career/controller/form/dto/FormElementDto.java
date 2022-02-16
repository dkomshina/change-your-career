package dmt.change.your.career.controller.form.dto;

import java.util.List;

import dmt.change.your.career.service.form.entity.FormElementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormElementDto {

    private Long id;
    private String title;
    private FormElementType type;
    private String description;
    private List<String> options;

}
