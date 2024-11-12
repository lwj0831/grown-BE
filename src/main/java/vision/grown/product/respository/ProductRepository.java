package vision.grown.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.product.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
