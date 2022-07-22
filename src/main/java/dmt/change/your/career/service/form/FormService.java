package dmt.change.your.career.service.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dmt.change.your.career.controller.form.dto.FormAnswerDto;
import dmt.change.your.career.controller.form.dto.FormBlockDto;
import dmt.change.your.career.controller.form.dto.FormDto;
import dmt.change.your.career.controller.form.dto.FormElementDto;
import dmt.change.your.career.controller.form.dto.FormSectionDto;
import dmt.change.your.career.ml.dto.MlChangellerDto;
import dmt.change.your.career.service.changeller.ChangellerMapper;
import dmt.change.your.career.service.changeller.ChangellerRepository;
import dmt.change.your.career.service.changeller.entity.Changeller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FormService {

    private final FormRepository formRepository;
    private final FormBlockRepository blockRepository;
    private final FormSectionRepository sectionRepository;
    private final FormElementRepository elementRepository;
    private final ChangellerRepository changellerRepository;
    private final ChangellerMapper mapper;

    public FormDto getForm() throws Exception {
        var form = formRepository.findNotOutdatedForm();
        var user = changellerRepository.save(Changeller.builder().build());
        return FormDto.builder()
                .sessionId(user.getId())
                .blocks(getBlocks(form.getId()))
                .build();
    }

    private List<FormBlockDto> getBlocks(long formId) {
        return blockRepository.findAllByFormIdAndOutdatedIsFalse(formId).stream()
                .map(block -> FormBlockDto.builder()
                        .id(block.getId())
                        .name(block.getName())
                        .sections(getSections(block.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<FormSectionDto> getSections(long blockId) {
        return sectionRepository.findAllByBlockIdAndOutdatedIsFalse(blockId).stream()
                .map(section -> FormSectionDto.builder()
                        .id(section.getId())
                        .name(section.getName())
                        .buttonTitle(section.getButtonTitle())
                        .elements(getElements(section.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<FormElementDto> getElements(long sectionId) {
        return elementRepository.findAllBySectionIdAndOutdatedIsFalse(sectionId).stream()
                .map(element -> FormElementDto.builder()
                        .id(element.getId())
                        .title(element.getTitle())
                        .type(element.getType())
                        .description(element.getDescription())
                        .options(element.getOptions())
                        .build())
                .collect(Collectors.toList());
    }

    public MlChangellerDto submitForm(long sessionId, List<FormAnswerDto<?>> dtos) {
        Map<Long, List<Object>> questionAnswerMap = new HashMap<>();
        dtos.forEach(dto -> {
                    if (questionAnswerMap.containsKey(dto.getId())) {
                        questionAnswerMap.get(dto.getId()).add(dto.getAnswer());
                    } else {
                        questionAnswerMap.put(dto.getId(), new ArrayList<>(Arrays.asList(dto.getAnswer())));
                    }
                }
        );
        Map<String, Object> features = new HashMap<>();
        questionAnswerMap.keySet().forEach(id -> {
            features.put(elementRepository.getMlName(id),
                    questionAnswerMap.get(id).stream().map(Object::toString).collect(Collectors.joining(" ")));
            features.put(elementRepository.getMlNumberName(id), questionAnswerMap.get(id).size());
        });

        var changeller = changellerRepository.findByIdOrThrow(sessionId);
        changeller.setFeatures(features);
        return mapper.map(changellerRepository.save(changeller));
    }
}
