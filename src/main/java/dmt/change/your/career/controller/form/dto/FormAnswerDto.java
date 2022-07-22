package dmt.change.your.career.controller.form.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormAnswerDto<T> {

    private long id;
    private T answer;

}
