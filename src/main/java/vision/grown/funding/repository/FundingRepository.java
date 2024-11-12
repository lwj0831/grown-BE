package vision.grown.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.funding.Funding;

public interface FundingRepository extends JpaRepository<Funding,Long> {
}
