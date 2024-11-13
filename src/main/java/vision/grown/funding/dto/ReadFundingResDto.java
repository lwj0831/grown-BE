package vision.grown.funding.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.center.CenterType;
import vision.grown.funding.Funding;
import vision.grown.product.ProductType;

import java.time.LocalDate;

@Getter
public class ReadFundingResDto {
    private String centerName;
    private CenterType centerType;
    private String fundingTitle;
    private ProductType productType;
    private int currentAmount;
    private double fundingRate;
    private double totalAmount;
    private LocalDate expireDate;
    private String url;

    @Builder
    public ReadFundingResDto(String centerName, CenterType centerType, String fundingTitle, ProductType productType, int currentAmount, double fundingRate, double totalAmount, LocalDate expireDate, String url) {
        this.centerName = centerName;
        this.centerType = centerType;
        this.fundingTitle = fundingTitle;
        this.productType = productType;
        this.currentAmount = currentAmount;
        this.fundingRate = fundingRate;
        this.totalAmount = totalAmount;
        this.expireDate = expireDate;
        this.url = url;
    }

    public ReadFundingResDto(Funding funding) {
        new ReadFundingResDtoBuilder().centerName(funding.getCenter().getCenterName())
                .centerType(funding.getCenter().getCenterType())
                .fundingTitle(funding.getFundingTitle())
                .productType(funding.getProductType())
                .currentAmount(funding.getCurrentAmount())
                .fundingRate(funding.getFundingRate())
                .totalAmount(funding.getTotalAmount())
                .expireDate(funding.getFundingExpireDate())
                .url(funding.getCenter().getCenterImageList().get(0).getUrl())
                .build();
    }
}
