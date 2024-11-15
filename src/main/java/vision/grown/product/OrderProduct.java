package vision.grown.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.funding.OrderFunding;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="order_product_id")
    private Long id;

    private int quantity;
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_funding_id")
    private OrderFunding orderFunding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public void setOrderFunding(OrderFunding orderFunding){
        this.orderFunding = orderFunding;
        orderFunding.getOrderProductList().add(this);
    }
    public int getOrderProductPrice(){
        return quantity*orderPrice;
    }

    @Builder
    public OrderProduct(int quantity, int orderPrice, Product product) {
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.product = product;
    }

    public static OrderProduct createOrderProduct(int quantity, int orderPrice, Product product, OrderFunding orderFunding){
        OrderProduct orderProduct = new OrderProductBuilder().quantity(quantity).orderPrice(orderPrice).product(product).build();
        orderProduct.setOrderFunding(orderFunding);
        product.removeStock(quantity);
        return orderProduct;
    }

}
