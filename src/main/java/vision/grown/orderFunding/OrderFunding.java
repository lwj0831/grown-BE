package vision.grown.orderFunding;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.funding.Funding;
import vision.grown.member.Member;
import vision.grown.product.Product;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderFunding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_funding_id")
    private Long id;

    private BigDecimal orderPrice;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="funding_id")
    private Funding funding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Builder
    public OrderFunding(BigDecimal orderPrice, int quantity, Member member, Funding funding, Product product) {
        this.orderPrice = orderPrice;
        this.quantity = quantity;
        this.member = member;
        this.funding = funding;
        this.product = product;
    }

    public static OrderFunding createOrderFunding(int quantity,Member member,Product product,Funding funding){
        OrderFunding orderFunding = OrderFunding.builder()
                .quantity(quantity)
                .orderPrice(BigDecimal.valueOf((long) product.getMinPrice() * quantity))
                .member(member)
                .product(product)
                .funding(funding)
                .build();
        product.removeStock(quantity);
        return orderFunding;
    }

}
