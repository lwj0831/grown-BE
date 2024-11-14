package vision.grown.product.respository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.product.Product;
import vision.grown.product.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @EntityGraph(attributePaths = {"member"})
    List<Product> findByProductType(ProductType productType, Pageable pageable);
}
