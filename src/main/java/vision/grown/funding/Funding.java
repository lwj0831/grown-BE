package vision.grown.funding;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.center.Center;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long id;
    private String content;
    private double fundingRate;
    private FundingStatus fundingStatus;
    private LocalDateTime fundingExpireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="center_id")
    private Center center;
}
