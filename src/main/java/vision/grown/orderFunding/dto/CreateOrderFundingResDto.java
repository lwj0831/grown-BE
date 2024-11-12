package vision.grown.orderFunding.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.ProductType;

import java.math.BigDecimal;

@Getter
public class CreateOrderFundingResDto {
    private int quantity;
    private ProductType productType;
    private BigDecimal orderPrice;

    @Builder
    public CreateOrderFundingResDto(int quantity, ProductType productType, BigDecimal orderPrice) {
        this.quantity = quantity;
        this.productType = productType;
        this.orderPrice = orderPrice;
    }
}
