package vision.grown.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    @Builder
    public FundingProduct(int requiredQuantity, ProductType productType, MeasurementUnit measurementUnit, Funding funding) {
        this.requiredQuantity = requiredQuantity;
        this.productType = productType;
        this.measurementUnit = measurementUnit;
        this.funding = funding;
    }
}
