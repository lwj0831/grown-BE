package vision.grown.funding.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vision.grown.funding.OrderFunding;

import java.util.List;

public interface OrderFundingRepository extends JpaRepository<OrderFunding, Long> {

    @Query("select of from OrderFunding of join fetch of.member m join fetch of.funding f where m.id=:memberId")
    List<OrderFunding> findOrderFundingByMemberId(@Param("memberId")Long memberId);
}
