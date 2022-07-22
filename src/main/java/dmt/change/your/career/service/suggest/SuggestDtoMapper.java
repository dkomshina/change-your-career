package dmt.change.your.career.service.suggest;

import dmt.change.your.career.controller.suggest.dto.ArticleDto;
import dmt.change.your.career.controller.suggest.dto.CourseDto;
import dmt.change.your.career.controller.suggest.dto.EventDto;
import dmt.change.your.career.controller.suggest.dto.VacancyDto;
import dmt.change.your.career.ml.dto.MlArticleDto;
import dmt.change.your.career.service.suggest.entity.Article;
import dmt.change.your.career.service.suggest.entity.Course;
import dmt.change.your.career.service.suggest.entity.Event;
import dmt.change.your.career.service.suggest.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SuggestDtoMapper {

    ArticleDto map(Article article);

    CourseDto map(Course course);

    EventDto map(Event event);

    VacancyDto map(Vacancy vacancy);

}
