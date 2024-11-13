package vision.grown.funding.dto;

import lombok.Getter;
import vision.grown.product.dto.OrderProductForm;

import java.util.List;

@Getter
public class CreateOrderFundingReqDto {
    private Long memberId;
    private Long fundingId;
    private List<OrderProductForm> orderProductFormList;

}
