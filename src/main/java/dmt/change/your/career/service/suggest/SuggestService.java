package dmt.change.your.career.service.suggest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dmt.change.your.career.controller.suggest.dto.ArticleDto;
import dmt.change.your.career.controller.suggest.dto.CourseDto;
import dmt.change.your.career.controller.suggest.dto.EventDto;
import dmt.change.your.career.controller.suggest.dto.VacancyDto;
import dmt.change.your.career.ml.dto.MlArticleDto;
import dmt.change.your.career.ml.dto.MlArticleFeatureDto;
import dmt.change.your.career.ml.dto.MlCourseDto;
import dmt.change.your.career.ml.dto.MlCourseFeatureDto;
import dmt.change.your.career.ml.dto.MlEventDto;
import dmt.change.your.career.ml.dto.MlEventFeatureDto;
import dmt.change.your.career.ml.dto.MlSuggestItemDto;
import dmt.change.your.career.ml.dto.MlVacancyDto;
import dmt.change.your.career.ml.dto.MlVacancyFeatureDto;
import dmt.change.your.career.service.changeller.ChangellerMapper;
import dmt.change.your.career.service.changeller.ChangellerRepository;
import dmt.change.your.career.service.changeller.entity.SuggestItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SuggestService {

    private final static int MAX_SUGGEST_COUNT = 10;

    private final ChangellerRepository changellerRepository;
    private final ArticleRepository articleRepository;
    private final CourseRepository courseRepository;
    private final EventRepository eventRepository;
    private final VacancyRepository vacancyRepository;
    private final SuggestDtoMapper suggestDtoMapper;
    private final ChangellerMapper changellerMapper;

    public List<ArticleDto> getArticles(long id) {
        return getArticles(changellerRepository.findByIdOrThrow(id).getArticles());
    }

    public List<ArticleDto> getArticles(List<SuggestItem> suggestItems) {

        if (suggestItems == null || suggestItems.isEmpty()) {
            return null;
        }

        List<Long> articleIds = getSuggestIds(suggestItems);

        if (articleIds.isEmpty()) {
            return null;
        }

        return articleRepository.findAllArticlesByIds(articleIds).stream().map(suggestDtoMapper::map).collect(Collectors.toList());
    }

    public List<EventDto> getEvents(long id) {
        return getEvents(changellerRepository.findByIdOrThrow(id).getEvents());
    }

    public List<EventDto> getEvents(List<SuggestItem> suggestItems) {

        if (suggestItems == null || suggestItems.isEmpty()) {
            return null;
        }

        List<Long> eventIds = getSuggestIds(suggestItems);

        if (eventIds.isEmpty()) {
            return null;
        }

        return eventRepository.findAllEventsByIds(eventIds).stream().map(suggestDtoMapper::map).collect(Collectors.toList());
    }

    public List<CourseDto> getCourses(long id) {
        return getCourses(changellerRepository.findByIdOrThrow(id).getCourses());
    }

    public List<CourseDto> getCourses(List<SuggestItem> suggestItems) {

        if (suggestItems == null || suggestItems.isEmpty()) {
            return null;
        }

        List<Long> courseIds = getSuggestIds(suggestItems);

        if (courseIds.isEmpty()) {
            return null;
        }

        return courseRepository.findAllCoursesByIds(courseIds).stream().map(suggestDtoMapper::map).collect(Collectors.toList());
    }

    public List<VacancyDto> getVacancies(long id) {
        return getVacancies(changellerRepository.findByIdOrThrow(id).getVacancies());
    }

    public List<VacancyDto> getVacancies(List<SuggestItem> suggestItems) {

        if (suggestItems == null || suggestItems.isEmpty()) {
            return null;
        }

        List<Long> vacancyIds = getSuggestIds(suggestItems);

        if (vacancyIds.isEmpty()) {
            return null;
        }

        return vacancyRepository.findAllVacanciesByIds(vacancyIds).stream().map(suggestDtoMapper::map).collect(Collectors.toList());
    }

    public List<ArticleDto> saveArticles(long changellerId, List<MlSuggestItemDto> suggestItemDtos) {
        var suggestItems = suggestItemDtos.stream().map(changellerMapper::map).collect(Collectors.toList());
        var changeller = changellerRepository.findByIdOrThrow(changellerId);
        changeller.setArticles(suggestItems);
        changellerRepository.save(changeller);

        return getArticles(suggestItems);
    }

    public List<EventDto> saveEvents(long changellerId, List<MlSuggestItemDto> suggestItemDtos) {
        var suggestItems = suggestItemDtos.stream().map(changellerMapper::map).collect(Collectors.toList());
        var changeller = changellerRepository.findByIdOrThrow(changellerId);
        changeller.setEvents(suggestItems);
        changellerRepository.save(changeller);

        return getEvents(suggestItems);
    }

    public List<VacancyDto> saveVacancies(long changellerId, List<MlSuggestItemDto> suggestItemDtos) {
        var suggestItems = suggestItemDtos.stream().map(changellerMapper::map).collect(Collectors.toList());
        var changeller = changellerRepository.findByIdOrThrow(changellerId);
        changeller.setVacancies(suggestItems);
        changellerRepository.save(changeller);

        return getVacancies(suggestItems);
    }

    public List<CourseDto> saveCourses(long changellerId, List<MlSuggestItemDto> suggestItemDtos) {
        var suggestItems = suggestItemDtos.stream().map(changellerMapper::map).collect(Collectors.toList());
        var changeller = changellerRepository.findByIdOrThrow(changellerId);
        changeller.setCourses(suggestItems);
        changellerRepository.save(changeller);

        return getCourses(suggestItems);
    }

    private List<Long> getSuggestIds(List<SuggestItem> suggestItems) {
        return suggestItems.stream().sorted(Comparator.comparing(SuggestItem::getScore, Comparator.reverseOrder()))
                .limit(MAX_SUGGEST_COUNT)
                .map(SuggestItem::getId)
                .collect(Collectors.toList());
    }

    public List<MlArticleDto> getNewArticles() {
        return articleRepository.findAllByMlSendIsFalse().stream().map(a ->
                MlArticleDto.builder()
                        .id(a.getId())
                        .features(MlArticleFeatureDto.builder()
                                .name(a.getTitle())
                                .description(a.getDescription())
                                .description_2(a.getDescription2())
                                .shocked_editor(a.isShockedEditor())
                                .category(a.getSubtitle())
                                .news(a.isNews())
                                .build()
                        )
                        .build()
        ).collect(Collectors.toList());
    }

    public List<MlEventDto> getNewEvents() {
        return eventRepository.findAllByMlSendIsFalse().stream().map(e ->
                MlEventDto.builder()
                        .id(e.getId())
                        .features(MlEventFeatureDto.builder()
                                .name(e.getTitle())
                                .description(e.getDescription())
                                .description_2(e.getDescription2())
                                .city(e.getCity())
                                .event_type(e.getSubtitle())
                                .build()
                        )
                        .build()
        ).collect(Collectors.toList());
    }

    public List<MlCourseDto> getNewCourses() {
        return courseRepository.findAllByMlSendIsFalse().stream().map(e ->
                MlCourseDto.builder()
                        .id(e.getId())
                        .features(MlCourseFeatureDto.builder()
                                .name(e.getTitle())
                                .description(e.getDescription())
                                .city(e.getCity())
                                .event_type(e.getSubtitle())
                                .build()
                        )
                        .build()
        ).collect(Collectors.toList());
    }

    public List<MlVacancyDto> getNewVacancy() {
        return vacancyRepository.findAllByMlSendIsFalse().stream().map(v ->
                MlVacancyDto.builder()
                        .id(v.getId())
                        .features(MlVacancyFeatureDto.builder()
                                .name(v.getTitle())
                                .description(v.getDescription())
                                .city(v.getLocation())
                                .work_mode(v.getWorkMode())
                                .company_id(v.getCompanyId())
                                .work_experience(v.getWorkExperience())
                                .company_area(v.getSubtitle())
                                .vacancy_type(v.getCompanyName())
                                .build()
                        )
                        .build()
        ).collect(Collectors.toList());
    }

    public void makeArticleOld(long id) {
        articleRepository.setArticleMlSendTrue(id);
    }

    public void makeEventOld(long id) {
        eventRepository.setEventMlSendTrue(id);
    }

    public void makeCourseOld(long id) {
        courseRepository.setCourseMlSendTrue(id);
    }

    public void makeVacancyOld(long id) {
        vacancyRepository.setVacancyMlSendTrue(id);
    }
}
