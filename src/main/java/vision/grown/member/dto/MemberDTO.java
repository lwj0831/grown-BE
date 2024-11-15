package vision.grown.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {
    private String name;
    private String email;
    private String password;
    private String phoneNum;
}
