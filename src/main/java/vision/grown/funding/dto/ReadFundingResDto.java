package vision.grown.funding.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import vision.grown.center.CenterType;
import vision.grown.funding.Funding;
import vision.grown.product.ProductType;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReadFundingResDto {
    private String centerName;
    private CenterType centerType;
    private String fundingTitle;
    private List<ProductType> productType;
    private double fundingRate;
    private LocalDate expireDate;
    private String url;

    @Builder
    public ReadFundingResDto(String centerName, CenterType centerType, String fundingTitle, List<ProductType> productType, double fundingRate, LocalDate expireDate, String url) {
        this.centerName = centerName;
        this.centerType = centerType;
        this.fundingTitle = fundingTitle;
        this.productType = productType;
        this.fundingRate = fundingRate;
        this.expireDate = expireDate;
        this.url = url;
    }

    public static ReadFundingResDto createReadFundingResDto(Funding funding) {
        return new ReadFundingResDtoBuilder().centerName(funding.getCenter().getCenterName())
                .centerType(funding.getCenter().getCenterType())
                .fundingTitle(funding.getFundingTitle())
                .productType(funding.getProductTypeList())
                .fundingRate(funding.getFundingRate())
                .expireDate(funding.getFundingExpireDate())
                .url(funding.getFundingImageList().get(0).getUrl())
                .build();
    }
}
