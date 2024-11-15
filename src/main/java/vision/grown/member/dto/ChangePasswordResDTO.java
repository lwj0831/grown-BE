package vision.grown.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordResDTO {
    private String name;
    private String message;
}
