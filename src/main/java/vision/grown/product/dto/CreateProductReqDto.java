package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import vision.grown.funding.dto.ImageForm;
import vision.grown.product.MeasurementUnit;
import vision.grown.product.ProductType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateProductReqDto {
    private Long memberId;
    private List<MultipartFile> imageList;
    private String productName;
    private LocalDate expireDate;
    private ProductType productType;
    private int minUnit;
    private int minPrice;
    private MeasurementUnit measurementUnit;
    private int totalQuantity;
    private int totalPrice;
    private String productContent;

}
