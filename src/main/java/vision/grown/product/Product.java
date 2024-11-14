package vision.grown.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.exception.NotEnoughStockException;
import vision.grown.member.Member;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    private String productName;
    private int totalPrice;
    private int totalQuantity;
    private int minUnit;
    private int minPrice;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private LocalDateTime productExpireDate;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public void removeStock(int quantity){
        int restStock = totalQuantity-quantity;
        if (restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        totalQuantity = restStock;
        if(totalQuantity<=0) productStatus = ProductStatus.COMP;
    }

}
