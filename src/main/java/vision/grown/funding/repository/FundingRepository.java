package vision.grown.funding.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.center.CenterType;
import vision.grown.funding.Funding;
import vision.grown.funding.FundingStatus;
import vision.grown.product.ProductType;

import java.util.List;
import java.util.Optional;


public interface FundingRepository extends JpaRepository<Funding,Long> {
    @Query("select f from Funding f join fetch f.center c where c.centerType =:centerType and f.fundingStatus =:fundingStatus order by f.fundingExpireDate")
    List<Funding> findFundingList(@Param("centerType") CenterType centerType,
                                  @Param("fundingStatus") FundingStatus fundingStatus,
                                  Pageable pageable);

    @Query("select f from Funding f join fetch f.center c join fetch f.fundingImageList fi where f.id =:fundingId")
    Optional<Funding> findFundingById(@Param("fundingId") Long fundingId);

    //distinct로 조인 후 결과 row수 늘어나는 것을 막아 paging가능
    @Query("SELECT DISTINCT f FROM Funding f WHERE f.fundingStatus = 'FUND' and f IN (SELECT fp.funding FROM FundingProduct fp WHERE fp.productType = :productType)")
    List<Funding> findByProductType(@Param("productType") ProductType productType, Pageable pageable);

    @Query("SELECT f FROM Funding f WHERE f.fundingStatus = 'FUND' ORDER BY f.fundingExpireDate ASC")
    List<Funding> findFirst5ByExpireDateAsc(Pageable pageable);


}
