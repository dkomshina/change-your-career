package dmt.change.your.career.service.changeller.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuggestItem implements Serializable {

    private long id;
    private double score;

    @Override
    public String toString() {
        return "SuggestItem{id=" + id + ", score=" + score + "}";
    }
}
