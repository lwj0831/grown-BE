package vision.grown.funding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.member.Member;
import vision.grown.product.OrderProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderFunding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_funding_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="funding_id")
    private Funding funding;

    @OneToMany(mappedBy = "orderFunding")
    @JsonIgnore
    private List<OrderProduct> orderProductList = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getOrderFundingList().add(this);
    }

    public void setFunding(Funding funding){
        this.funding = funding;
        funding.getOrderFundingList().add(this);
    }


    public static OrderFunding createOrderFunding(Member member, Funding funding){
        OrderFunding orderFunding = new OrderFunding();
        orderFunding.setMember(member);
        orderFunding.setFunding(funding);
        return orderFunding;
    }

    public int getOrderFundingPrice(){
        return orderProductList.stream().mapToInt(OrderProduct::getOrderProductPrice).sum();
    }
    public int getOrderFundingQuantity(){
        return orderProductList.stream().mapToInt(OrderProduct::getQuantity).sum();
    }

}
