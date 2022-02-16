package dmt.change.your.career.service.form;

import java.util.List;
import java.util.stream.Collectors;

import dmt.change.your.career.controller.form.dto.FormBlockDto;
import dmt.change.your.career.controller.form.dto.FormDto;
import dmt.change.your.career.controller.form.dto.FormElementDto;
import dmt.change.your.career.controller.form.dto.FormSectionDto;
import dmt.change.your.career.service.user.ChangellerRepository;
import dmt.change.your.career.service.user.entity.Changeller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FormQueryService {

    private final FormRepository formRepository;
    private final FormBlockRepository blockRepository;
    private final FormSectionRepository sectionRepository;
    private final FormElementRepository elementRepository;
    private final ChangellerRepository userRepository;

    public FormDto getForm() throws Exception {
        var form = formRepository.findNotOutdatedForm();
        var user = userRepository.save(Changeller.builder().build());
        return FormDto.builder()
                .sessionId(user.getId())
                .blocks(getBlocks(form.getId()))
                .build();
    }

    public List<FormBlockDto> getBlocks(long formId) {
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
}
