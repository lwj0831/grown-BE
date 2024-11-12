package vision.grown.orderFunding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.orderFunding.OrderFunding;

public interface OrderFundingRepository extends JpaRepository<OrderFunding, Long> {
}
