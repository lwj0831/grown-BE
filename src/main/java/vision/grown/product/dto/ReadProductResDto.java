package vision.grown.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReadProductResDto<T> {
    private List<T> result;

    public ReadProductResDto(List<T> result) {
        this.result = result;
    }
}
