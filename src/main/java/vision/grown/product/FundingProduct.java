package vision.grown.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.funding.Funding;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FundingProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="funding_product_id")
    private Long id;
    private int requiredQuantity;
    private int currentQuantity;
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    public double getFundraisingRate(){
        return (double) currentQuantity / requiredQuantity *100;
    }
}
