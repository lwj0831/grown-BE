package vision.grown.center;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="center_id")
    private Long id;
    private String centerName;
    private String centerInfo;
    private CenterType centerType;
    @Embedded
    private Address address;

}
