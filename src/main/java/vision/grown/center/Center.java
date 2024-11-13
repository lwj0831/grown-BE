package vision.grown.center;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private CenterType centerType;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "center")
    @JsonIgnore
    private List<CenterImage> centerImageList = new ArrayList<>();

}
