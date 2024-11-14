package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    public void setFundingStatus(FundingStatus fundingStatus){
        this.fundingStatus = fundingStatus;
    }

    public double getFundingRate(){
        return Math.floor((double) getCurrentQuantity() / getTotalRequiredQuantity() * 100);
    }
    public int getCurrentPrice(){//orderFunding N
        return orderFundingList.stream().mapToInt(OrderFunding::getOrderFundingPrice).sum();
    }

    public int getCurrentQuantity(){//orderFunding N
        return orderFundingList.stream().mapToInt(OrderFunding::getOrderFundingQuantity).sum();
    }
    public int getTotalRequiredQuantity(){
        return fundingProductList.stream().mapToInt(FundingProduct::getRequiredQuantity).sum();
    }
    public List<ProductType> getProductTypeList(){
        return fundingProductList.stream().map(FundingProduct::getProductType).distinct().toList();
    }

}
