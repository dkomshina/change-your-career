package dmt.change.your.career.controller.suggest.dto;


import dmt.change.your.career.ml.dto.Reaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDto {

    private long sessionId;
    private long suggestId;
    private Reaction reaction;
}
