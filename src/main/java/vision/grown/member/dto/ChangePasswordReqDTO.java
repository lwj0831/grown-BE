package vision.grown.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordReqDTO {
    private String email;
    private String password;
}
