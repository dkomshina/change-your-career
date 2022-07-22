package dmt.change.your.career.ml.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MlArticleDto {

    private long id;
    private MlArticleFeatureDto features;

}
