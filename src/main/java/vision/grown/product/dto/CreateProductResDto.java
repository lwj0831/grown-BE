package vision.grown.product.dto;

import lombok.Getter;

@Getter
public class CreateProductResDto {
    private Long productId;

    public CreateProductResDto(Long productId) {
        this.productId = productId;
    }
}
