package vision.grown.funding.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchFundingResDto<T> {
    private List<T> result;

    public SearchFundingResDto(List<T> result) {
        this.result = result;
    }
}
