package vision.grown.funding.dto;

import lombok.Getter;

@Getter
public class CreateFundingResDto {
    private Long fundingId;
    private String fundingTitle;

    public CreateFundingResDto(Long fundingId, String fundingTitle) {
        this.fundingId = fundingId;
        this.fundingTitle = fundingTitle;
    }
}
