package vision.grown.product.respository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.product.Product;
import vision.grown.product.ProductStatus;
import vision.grown.product.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @EntityGraph(attributePaths = {"member"})
    List<Product> findByProductType(ProductType productType, Pageable pageable);
    @Query("select p from Product p join fetch p.member m where p.productType=:productType and p.productStatus=:productStatus order by p.productExpireDate")
    List<Product> findProductList(@Param("productType")ProductType productType,@Param("productStatus") ProductStatus productStatus, Pageable pageable);

    @Query("select p from Product p join fetch p.member join fetch p.productImageList where p.id = :productId")
    Optional<Product> findProduct(@Param("productId")Long productId);
}
