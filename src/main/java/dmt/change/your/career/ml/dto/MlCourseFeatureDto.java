package dmt.change.your.career.ml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MlCourseFeatureDto {

    private String name;
    private String description;
    private String city;
    private String event_type;
}
