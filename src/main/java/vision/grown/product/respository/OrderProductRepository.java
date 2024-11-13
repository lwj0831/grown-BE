package vision.grown.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.product.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
}
