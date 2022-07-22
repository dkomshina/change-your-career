package dmt.change.your.career.controller.suggest.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;
    private String thumbnailUrl;
    private String title;
    private String subtitle;
    private String description;
    private LocalDate date;
    private String link;

}
