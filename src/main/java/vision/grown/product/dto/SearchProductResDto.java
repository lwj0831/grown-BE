package vision.grown.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchProductResDto<T> {
    private List<T> result;

    public SearchProductResDto(List<T> result) {
        this.result = result;
    }
}
