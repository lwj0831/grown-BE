package vision.grown.funding.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.funding.OrderFunding;
import vision.grown.product.OrderProduct;
import vision.grown.product.ProductType;

import java.util.List;

@Getter
public class ReadOrderFundingForm {
    private String fundingTitle;
    private List<ProductType> productType;
    private List<String> memberName;
    private int orderFundingPrice;

    @Builder
    public ReadOrderFundingForm(String fundingTitle, List<ProductType> productType, List<String> memberName, int orderFundingPrice) {
        this.fundingTitle = fundingTitle;
        this.productType = productType;
        this.memberName = memberName;
        this.orderFundingPrice = orderFundingPrice;
    }

    public static ReadOrderFundingForm createReadOrderFundingResDto(OrderFunding orderFunding){
        List<OrderProduct> orderProductList = orderFunding.getOrderProductList();
        return ReadOrderFundingForm.builder()
                .fundingTitle(orderFunding.getFunding().getFundingTitle())
                .productType(orderProductList.stream().map(of->of.getProduct().getProductType()).toList())
                .memberName(orderProductList.stream().map(op->op.getProduct().getMember().getName()).toList())
                .orderFundingPrice(orderFunding.getOrderFundingPrice()).build();
    }
}
