package dmt.change.your.career.controller.suggest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {

    private Long id;
    private String thumbnailUrl;
    private String title;
    private String subtitle;
    private String location;
    private String companyName;
    private String link;

}
