package vision.grown.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.product.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
