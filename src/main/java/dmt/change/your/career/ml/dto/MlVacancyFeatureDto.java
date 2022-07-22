package dmt.change.your.career.ml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MlVacancyFeatureDto {

    private String name;
    private String description;
    private String city;
    private String work_mode;
    private String company_id;
    private String work_experience;
    private String company_area;
    private String vacancy_type;
}
