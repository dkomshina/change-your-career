package dmt.change.your.career.controller.suggest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private long id;
    private String thumbnailUrl;
    private String title;
    private String subtitle;
    private String description;
    private String link;

}
