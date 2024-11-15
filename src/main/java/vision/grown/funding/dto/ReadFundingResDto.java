package vision.grown.funding.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReadFundingResDto<T> {
    private List<T> result;

    public ReadFundingResDto(List<T> result) {
        this.result = result;
    }
}
