package vision.grown.funding.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.center.CenterType;
import vision.grown.product.dto.FundingProductDetailForm;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReadFundingDetailResDto {
    private List<ImageForm> imageList;
    private String centerName;
    private CenterType centerType;
    private String fundingTitle;
    private String fundingContent;
    private LocalDate expireDate;
    private double fundingRate;
    private List<FundingProductDetailForm> fundingProductList;

    @Builder
    public ReadFundingDetailResDto(List<ImageForm> imageList, String centerName, CenterType centerType, String fundingTitle, String fundingContent, LocalDate expireDate, double fundingRate, List<FundingProductDetailForm> fundingProductList) {
        this.imageList = imageList;
        this.centerName = centerName;
        this.centerType = centerType;
        this.fundingTitle = fundingTitle;
        this.fundingContent = fundingContent;
        this.expireDate = expireDate;
        this.fundingRate = fundingRate;
        this.fundingProductList = fundingProductList;
    }
}
