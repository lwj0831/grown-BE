package vision.grown.product.dto;

import lombok.Builder;
import lombok.Getter;
import vision.grown.product.MeasurementUnit;
import vision.grown.product.Product;
import vision.grown.product.ProductImage;
import vision.grown.product.ProductType;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReadProductDetailResDto {
    private List<String> imageUrlList;
    private String productName;
    private String productContent;
    private String memberName;
    private ProductType productType;
    private LocalDate expireDate;
    private int minPrice;
    private int minUnit;
    private MeasurementUnit measurementUnit;

    @Builder
    public ReadProductDetailResDto(List<String> imageUrlList, String productName, String productContent, String memberName, ProductType productType, LocalDate expireDate, int minPrice, int minUnit, MeasurementUnit measurementUnit) {
        this.imageUrlList = imageUrlList;
        this.productName = productName;
        this.productContent = productContent;
        this.memberName = memberName;
        this.productType = productType;
        this.expireDate = expireDate;
        this.minPrice = minPrice;
        this.minUnit = minUnit;
        this.measurementUnit = measurementUnit;
    }

    public static ReadProductDetailResDto createReadProductDetailResDto(Product product){
        List<String> imageUrlList = product.getProductImageList().stream().map(ProductImage::getUrl).toList();
        return ReadProductDetailResDto.builder()
                .imageUrlList(imageUrlList)
                .productName(product.getProductName())
                .productContent(product.getProductContent())
                .memberName(product.getMember().getName())
                .productType(product.getProductType())
                .expireDate(product.getProductExpireDate())
                .minPrice(product.getMinPrice())
                .minUnit(product.getMinUnit())
                .measurementUnit(product.getMeasurementUnit())
                .build();
    }
}
