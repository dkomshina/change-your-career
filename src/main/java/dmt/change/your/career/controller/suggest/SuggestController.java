package dmt.change.your.career.controller.suggest;

import java.util.List;

import dmt.change.your.career.controller.suggest.dto.ArticleDto;
import dmt.change.your.career.controller.suggest.dto.CourseDto;
import dmt.change.your.career.controller.suggest.dto.EventDto;
import dmt.change.your.career.controller.suggest.dto.ReactionDto;
import dmt.change.your.career.controller.suggest.dto.VacancyDto;
import dmt.change.your.career.ml.MlClient;
import dmt.change.your.career.ml.dto.MlReactionDto;
import dmt.change.your.career.service.suggest.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/dmt/suggest"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SuggestController {

    private final SuggestService suggestService;
    private final MlClient mlClient;

    @GetMapping("/article")
    public List<ArticleDto> getArticles(
            @RequestParam("sessionId") long sessionId
    ) {
        var articleSuggest = suggestService.getArticles(sessionId);
        if (articleSuggest == null) {
            var suggests = mlClient.getMlArticleSuggests(sessionId);

            return suggestService.saveArticles(sessionId, suggests);
        }
        return articleSuggest;
    }

    @GetMapping("/event")
    public List<EventDto> getEvents(
            @RequestParam("sessionId") long sessionId
    ) {
        var eventSuggest = suggestService.getEvents(sessionId);
        if (eventSuggest == null) {
            return suggestService.saveEvents(sessionId, mlClient.getMlEventSuggest(sessionId));
        }
        return eventSuggest;
    }

    @GetMapping("/vacancy")
    public List<VacancyDto> getVacancies(
            @RequestParam("sessionId") long sessionId
    ) {
        var courseSuggest = suggestService.getVacancies(sessionId);
        if (courseSuggest == null) {
            return suggestService.saveVacancies(sessionId, mlClient.getMlVacancySuggest(sessionId));
        }
        return courseSuggest;
    }

    @GetMapping("/course")
    public List<CourseDto> getCourses(
            @RequestParam("sessionId") long sessionId
    ) {
        var courseSuggest = suggestService.getCourses(sessionId);
        if (courseSuggest == null) {
            return suggestService.saveCourses(sessionId, mlClient.getMlCourseSuggest(sessionId));
        }
        return courseSuggest;
    }

    @PostMapping("/article/feedback")
    public void createReaction(@RequestBody ReactionDto reactionDto) {
        mlClient.createArticleReaction(MlReactionDto.builder()
                .userId(reactionDto.getSessionId())
                .itemId(reactionDto.getSuggestId())
                .reaction(reactionDto.getReaction())
                .build());
    }
}
