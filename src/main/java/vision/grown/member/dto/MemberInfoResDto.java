package vision.grown.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResDto {
    private String name;
    private String phoneNum;
    private Long memberId;
    private int memberFundingPrice;
}
