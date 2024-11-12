package vision.grown.orderFunding.dto;

import lombok.Getter;

@Getter
public class CreateOrderFundingReqDto {
    private Long productId;
    private int quantity;
    private Long memberId;
    private Long fundingId;
}
