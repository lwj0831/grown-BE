package vision.grown.product.dto;

import lombok.Getter;
import vision.grown.product.ProductStatus;
import vision.grown.product.ProductType;

@Getter
public class ReadProductReqDto {
    private ProductStatus productStatus;
    private ProductType productType;
}
