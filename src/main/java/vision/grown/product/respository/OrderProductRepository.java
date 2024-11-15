package vision.grown.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.product.OrderProduct;
import vision.grown.product.ProductType;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    //productType 사용 위해 join, 엔티티 반환하는게 아니고 sum하는 거라 fetch join 필요없음
    @Query("select sum(op.quantity) from OrderProduct op join op.product p where op.orderFunding.id =:orderFundingId and p.productType=:productType")
    int getCurrentQuantity(@Param("orderFundingId")Long orderFundingId, @Param("productType") ProductType productType);

}
