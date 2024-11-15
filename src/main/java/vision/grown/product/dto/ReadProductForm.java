package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.MeasurementUnit;
import vision.grown.product.Product;
import vision.grown.product.ProductType;

import java.time.LocalDate;

@Getter
public class ReadProductForm {
    private Long productId;
    private String image;
    private String productName;
    private String memberName;
    private ProductType productType;
    private LocalDate expireDate;
    private int minPrice;
    private int minUnit;
    private MeasurementUnit measurementUnit;

    @Builder
    public ReadProductForm(Long productId, String image, String productName, String memberName, ProductType productType, LocalDate expireDate, int minPrice, int minUnit, MeasurementUnit measurementUnit) {
        this.productId = productId;
        this.image = image;
        this.productName = productName;
        this.memberName = memberName;
        this.productType = productType;
        this.expireDate = expireDate;
        this.minPrice = minPrice;
        this.minUnit = minUnit;
        this.measurementUnit = measurementUnit;
    }

    public static ReadProductForm createReadProductForm(Product product){
        return ReadProductForm.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .productType(product.getProductType())
                .minUnit(product.getMinUnit())
                .minPrice(product.getMinPrice())
                .expireDate(product.getProductExpireDate())
                .image(product.getProductImageList().get(0).getUrl())
                .memberName(product.getMember().getName())
                .measurementUnit(product.getMeasurementUnit())
                .build();
    }
}
