package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.ProductType;

@Getter
public class FundingProductDetailForm {
    private ProductType productType;
    private int currentQuantity;
    private int requiredQuantity;
    private double raisingRate;

    @Builder
    public FundingProductDetailForm(ProductType productType, int currentQuantity, int requiredQuantity, double raisingRate) {
        this.productType = productType;
        this.currentQuantity = currentQuantity;
        this.requiredQuantity = requiredQuantity;
        this.raisingRate = raisingRate;
    }
}
