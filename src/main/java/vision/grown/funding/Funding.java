package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.center.Center;
import vision.grown.orderFunding.OrderFunding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funding_id")
    private Long id;
    private String content;
    @Min(value=0, message = "Funding amount must be at least 0")
    private int fundingAmount;
    private FundingStatus fundingStatus;
    private LocalDateTime fundingExpireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="center_id")
    private Center center;

    @OneToMany(mappedBy = "funding")
    @JsonIgnore
    private List<OrderFunding> orderFundingList = new ArrayList<>();

    public double getFundingRate(){
        return (double) orderFundingList.stream().mapToInt(OrderFunding::getQuantity).sum() / fundingAmount * 100;
    }
}
