package vision.grown.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vision.grown.product.MeasurementUnit;
import vision.grown.product.ProductType;

@Getter
@Setter
public class FundingProductForm {
    private ProductType productType;
    private int requiredQuantity;
    private MeasurementUnit measurementUnit;
}
