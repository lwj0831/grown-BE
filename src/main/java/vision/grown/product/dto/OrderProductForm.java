package vision.grown.product.dto;

import lombok.Getter;

@Getter
public class OrderProductForm {
    private Long productId;
    private int orderQuantity;
    private int orderPrice;
}
