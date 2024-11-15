package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
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
        return Math.floor((double) getTotalCurrentQuantity() / getTotalRequiredQuantity() * 100);
    }
    public int getCurrentPrice(){//orderFunding N, fetch join 적용 못하고 지연로딩됨
        return orderFundingList.stream().mapToInt(OrderFunding::getOrderFundingPrice).sum();
    }

    public int getTotalCurrentQuantity(){//orderFunding N, fetch join 적용 못하고 지연로딩됨
        return orderFundingList.stream().mapToInt(OrderFunding::getOrderFundingQuantity).sum();
    }
    public int getTotalRequiredQuantity(){
        return fundingProductList.stream().mapToInt(FundingProduct::getRequiredQuantity).sum();
    }
    public List<ProductType> getProductTypeList(){
        return fundingProductList.stream().map(FundingProduct::getProductType).distinct().toList();
    }
    @Builder
    public Funding(String fundingTitle, String fundingContent, FundingStatus fundingStatus, LocalDate fundingExpireDate, Center center) {
        this.fundingTitle = fundingTitle;
        this.fundingContent = fundingContent;
        this.fundingStatus = fundingStatus;
        this.fundingExpireDate = fundingExpireDate;
        this.center = center;
    }
}
