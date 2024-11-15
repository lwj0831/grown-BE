package vision.grown.funding.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vision.grown.center.CenterType;
import vision.grown.product.dto.FundingProductForm;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateFundingReqDto {
    private String centerName;
    private CenterType centerType;
    private List<MultipartFile> imageList;
    private String fundingTitle;
    private String fundingContent;
    private LocalDate expireDate;
    private List<FundingProductForm> fundingProductFormList;
}
