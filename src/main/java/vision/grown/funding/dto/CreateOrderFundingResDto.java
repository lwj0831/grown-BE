package vision.grown.funding.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.ProductType;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateOrderFundingResDto {
    private String centerName;
    private List<String> memberNameList;
    private int totalOrderPrice;
    private double contributionRate;


    @Builder
    public CreateOrderFundingResDto(String centerName, List<String> memberNameList, int totalOrderPrice, double contributionRate) {
        this.centerName = centerName;
        this.memberNameList = memberNameList;
        this.totalOrderPrice = totalOrderPrice;
        this.contributionRate = contributionRate;
    }
}
