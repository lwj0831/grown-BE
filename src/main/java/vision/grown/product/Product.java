package vision.grown.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.exception.NotEnoughStockException;
import vision.grown.member.Member;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    private String productName;
    private String productContent;
    private int totalPrice;
    private int totalQuantity;
    private int minUnit;
    private int minPrice;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private LocalDate productExpireDate;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    private MeasurementUnit MeasurementUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductImage> productImageList = new ArrayList<>();

    public void removeStock(int quantity){
        int restStock = totalQuantity-quantity;
        if (restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        totalQuantity = restStock;
        if(totalQuantity<=0) productStatus = ProductStatus.COMP;
    }

    @Builder
    public Product(String productName, String productContent, int totalPrice, int totalQuantity, int minUnit, int minPrice, ProductStatus productStatus, LocalDate productExpireDate, ProductType productType, vision.grown.product.MeasurementUnit measurementUnit, Member member) {
        this.productName = productName;
        this.productContent = productContent;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.minUnit = minUnit;
        this.minPrice = minPrice;
        this.productStatus = productStatus;
        this.productExpireDate = productExpireDate;
        this.productType = productType;
        MeasurementUnit = measurementUnit;
        this.member = member;
    }

}
