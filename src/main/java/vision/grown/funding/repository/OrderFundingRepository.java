package vision.grown.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.funding.OrderFunding;

public interface OrderFundingRepository extends JpaRepository<OrderFunding, Long> {
}
