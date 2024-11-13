package vision.grown.funding.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.center.CenterType;
import vision.grown.funding.Funding;
import vision.grown.funding.FundingStatus;



public interface FundingRepository extends JpaRepository<Funding,Long> {
    @Query("select f from Funding f join fetch f.center c where c.centerType =:centerType and f.fundingStatus =:fundingStatus order by f.fundingExpireDate")
    Page<Funding> findFundingList(@Param("centerType") CenterType centerType,
                                  @Param("fundingStatus") FundingStatus fundingStatus,
                                  Pageable pageable);

}
