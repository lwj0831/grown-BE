package vision.grown.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class FindProductResDto<T> {
    private List<T> result;

    public FindProductResDto(List<T> result) {
        this.result = result;
    }
}
