package dmt.change.your.career.ml.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MlArticleFeatureDto {

    private String name;
    private String description;
    private String description_2;
    private boolean shocked_editor;
    private String category;
    private boolean news;

}
