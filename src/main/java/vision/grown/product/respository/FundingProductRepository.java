package vision.grown.product.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.product.FundingProduct;
import vision.grown.product.ProductType;

import java.util.Optional;

public interface FundingProductRepository extends JpaRepository<FundingProduct,Long> {
    @Query("select sum(fp.requiredQuantity) from FundingProduct fp where fp.productType =:productType")
    int getRequiredQuantityByProductType(@Param("productType") ProductType productType);
}
