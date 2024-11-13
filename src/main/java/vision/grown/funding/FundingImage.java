package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FundingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_image_id")
    private Long id;
    private int imageOrder;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="funding_id")
    private Funding funding;
}
