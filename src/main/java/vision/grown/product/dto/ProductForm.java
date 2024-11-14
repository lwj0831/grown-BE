package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.MeasurementUnit;

@Getter
public class ProductForm {
    private Long productId;
    private String productName;
    private String memberName;
    private int minPrice;
    private int minUnit;
    private MeasurementUnit measurementUnit;

    @Builder
    public ProductForm(Long productId, String productName, String memberName, int minPrice, int minUnit, MeasurementUnit measurementUnit) {
        this.productId = productId;
        this.productName = productName;
        this.memberName = memberName;
        this.minPrice = minPrice;
        this.minUnit = minUnit;
        this.measurementUnit = measurementUnit;
    }
}
