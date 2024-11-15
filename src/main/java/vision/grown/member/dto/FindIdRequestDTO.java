package vision.grown.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FindIdRequestDTO {
    private String name;
    private String phoneNum;
}
