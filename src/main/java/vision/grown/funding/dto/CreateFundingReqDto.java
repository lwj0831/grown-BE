package vision.grown.funding.dto;

import lombok.Getter;
import vision.grown.center.CenterType;
import vision.grown.product.dto.FundingProductForm;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CreateFundingReqDto {
    private String centerName;
    private CenterType centerType;
    private List<String> imageUrlList;
    private String fundingTitle;
    private String fundingContent;
    private LocalDate expireDate;
    private List<FundingProductForm> fundingProductFormList;
}
