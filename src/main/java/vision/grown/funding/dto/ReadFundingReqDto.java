package vision.grown.funding.dto;

import lombok.Getter;
import vision.grown.center.CenterType;
import vision.grown.funding.FundingStatus;

@Getter
public class ReadFundingReqDto {
    private FundingStatus fundingStatus;
    private CenterType centerType;
}
