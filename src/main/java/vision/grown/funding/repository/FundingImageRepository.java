package vision.grown.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.funding.FundingImage;

public interface FundingImageRepository extends JpaRepository<FundingImage,Long> {
}
