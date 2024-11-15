package vision.grown.product.dto;

import lombok.Getter;
import vision.grown.product.MeasurementUnit;
import vision.grown.product.ProductType;

@Getter
public class FundingProductForm {
    private ProductType productType;
    private int requiredQuantity;
    private MeasurementUnit measurementUnit;
}
