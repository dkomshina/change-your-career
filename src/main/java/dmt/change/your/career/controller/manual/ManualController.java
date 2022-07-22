package dmt.change.your.career.controller.manual;

import dmt.change.your.career.ml.MlClient;
import dmt.change.your.career.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/manual"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ManualController {

    private final MlClient mlClient;
    private final SuggestService suggestService;

    @PostMapping("/article")
    public void postArticles() {
        suggestService.getNewArticles().forEach(a -> {
            try {
                mlClient.sendArticle(a);
            } catch (Exception ignored) {

            }
            suggestService.makeArticleOld(a.getId());
        });
    }

    @PostMapping("/event")
    public void postEvents() {
        suggestService.getNewEvents().forEach(e -> {
            try {
                mlClient.sendEvent(e);
            } catch (Exception ignored) {

            }
            suggestService.makeEventOld(e.getId());
        });
    }

    @PostMapping("/course")
    public void postCourses() {
        suggestService.getNewCourses().forEach(c -> {
             try {
                mlClient.sendCourse(c);
            } catch (Exception ignored) {

            }
            suggestService.makeCourseOld(c.getId());
        });
    }

    @PostMapping("/vacancy")
    public void postVacancies() {
        suggestService.getNewVacancy().forEach(v -> {
            try {
                mlClient.sendVacancy(v);
            } catch (Exception ignored) {

            }
            suggestService.makeVacancyOld(v.getId());
        });
    }
}
