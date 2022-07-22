package dmt.change.your.career.ml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dmt.change.your.career.ml.dto.MlArticleDto;
import dmt.change.your.career.ml.dto.MlChangellerDto;
import dmt.change.your.career.ml.dto.MlCourseDto;
import dmt.change.your.career.ml.dto.MlEventDto;
import dmt.change.your.career.ml.dto.MlReactionDto;
import dmt.change.your.career.ml.dto.MlSuggestItemDto;
import dmt.change.your.career.ml.dto.MlVacancyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
@RequiredArgsConstructor
public class MlClient {

    private final static String SERVICE_URL = "http://84.252.140.26/ml/";
    private final RestTemplate restTemplate;

    public void createMlUser(MlChangellerDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("users")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlChangellerDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }

    public List<MlSuggestItemDto> getMlArticleSuggests(long changellerId) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("articles")
                .queryParam("user_id", changellerId)
                .build().toUriString();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        ParameterizedTypeReference<List<MlSuggestItemDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public List<MlSuggestItemDto> getMlEventSuggest(long changellerId) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("events")
                .queryParam("user_id", changellerId)
                .build().toUriString();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        ParameterizedTypeReference<List<MlSuggestItemDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public List<MlSuggestItemDto> getMlCourseSuggest(long changellerId) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("courses")
                .queryParam("user_id", changellerId)
                .build().toUriString();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        ParameterizedTypeReference<List<MlSuggestItemDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public List<MlSuggestItemDto> getMlVacancySuggest(long changellerId) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("vacancies")
                .queryParam("user_id", changellerId)
                .build().toUriString();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        ParameterizedTypeReference<List<MlSuggestItemDto>> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType).getBody();
    }

    public void createArticleReaction(MlReactionDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("articles/reactions")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlReactionDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }

    public void sendArticle(MlArticleDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("articles")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlArticleDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }

    public void sendEvent(MlEventDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("events")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlEventDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }

    public void sendCourse(MlCourseDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("courses")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlCourseDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }

    public void sendVacancy(MlVacancyDto requestDto) {
        String url = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
                .path("vacancies")
                .build().toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MlVacancyDto> requestEntity = new HttpEntity<>(requestDto, headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, ResponseEntity.class);
    }
}
