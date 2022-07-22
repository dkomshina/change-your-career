package dmt.change.your.career.service.changeller;

import dmt.change.your.career.ml.dto.MlChangellerDto;
import dmt.change.your.career.ml.dto.MlSuggestItemDto;
import dmt.change.your.career.service.changeller.entity.Changeller;
import dmt.change.your.career.service.changeller.entity.SuggestItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ChangellerMapper {

    MlChangellerDto map(Changeller changeller);

    @Mapping(source = "item_id", target = "id")
    @Mapping(source = "est", target = "score")
    SuggestItem map(MlSuggestItemDto suggestItemDto);
}
