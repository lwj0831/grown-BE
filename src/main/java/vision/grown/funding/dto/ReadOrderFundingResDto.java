package vision.grown.funding.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReadOrderFundingResDto<T>{
    private List<T> result;

    public ReadOrderFundingResDto(List<T> result) {
        this.result = result;
    }
}
