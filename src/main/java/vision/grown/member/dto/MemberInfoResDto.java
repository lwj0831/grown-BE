package vision.grown.member.dto;

import lombok.Builder;

@Builder
public class MemberInfoResDto {
    private String name;
    private String phoneNum;
    private Long memberId;
    private int memberFundingPrice;

    @Builder
    public MemberInfoResDto(String name, String phoneNum, Long memberId, int memberFundingPrice) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.memberId = memberId;
        this.memberFundingPrice = memberFundingPrice;
    }
}
