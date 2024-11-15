package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateProductResDto {
    private Long productId;

    public CreateProductResDto(Long productId) {
        this.productId = productId;
    }
}
