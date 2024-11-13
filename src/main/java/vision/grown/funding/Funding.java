package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.center.Center;
import vision.grown.product.FundingProduct;
import vision.grown.product.ProductType;
import java.time.LocalDate;
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
    private String fundingTitle;
    private String fundingContent;
    @Min(value=0, message = "Funding amount must be at least 0")
    @Enumerated(EnumType.STRING)
    private FundingStatus fundingStatus;
    private LocalDate fundingExpireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="center_id")
    private Center center;

    @OneToMany(mappedBy = "funding")
    @JsonIgnore
    private List<OrderFunding> orderFundingList = new ArrayList<>();

    @OneToMany(mappedBy = "funding")
    @JsonIgnore
    private List<FundingProduct> fundingProductList = new ArrayList<>();

    @OneToMany(mappedBy = "funding")
    @JsonIgnore
    private List<FundingImage> fundingImageList = new ArrayList<>();

    public double getFundingRate(){
        return (double) getCurrentAmount() / getTotalRequiredAmount() * 100;
    }
    public int getCurrentRaisingPrice(){//orderFunding N
        return orderFundingList.stream().mapToInt(OrderFunding::getTotalOrderPrice).sum();
    }

    public int getCurrentAmount(){//orderFunding N
        return orderFundingList.stream().mapToInt(OrderFunding::getTotalOrderQuantity).sum();
    }
    public int getTotalRequiredAmount(){
        return fundingProductList.stream().mapToInt(FundingProduct::getRequiredQuantity).sum();
    }
    public List<ProductType> getProductTypeList(){
        return fundingProductList.stream().map(FundingProduct::getProductType).toList();
    }
}
