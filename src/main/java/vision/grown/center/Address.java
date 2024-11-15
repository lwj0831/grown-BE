package vision.grown.center;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String si;
    private String dong;
    private String gu;

}
