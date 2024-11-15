package vision.grown.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.member.Member;
import vision.grown.member.repository.MemberRepository;
import vision.grown.product.Product;
import vision.grown.product.ProductImage;
import vision.grown.product.ProductStatus;
import vision.grown.product.ProductType;
import vision.grown.product.dto.*;
import vision.grown.product.respository.ProductImageRepository;
import vision.grown.product.respository.ProductRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ProductImageRepository productImageRepository;

    @Transactional
    public CreateProductResDto createProduct(CreateProductReqDto dto){
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();

        Product product = Product.builder().productName(dto.getProductName())
                .productContent(dto.getProductContent())
                .productStatus(ProductStatus.SALE)
                .productType(dto.getProductType())
                .productExpireDate(dto.getExpireDate())
                .measurementUnit(dto.getMeasurementUnit())
                .totalPrice(dto.getTotalPrice())
                .totalQuantity(dto.getTotalQuantity())
                .minUnit(dto.getMinUnit())
                .minPrice(dto.getMinPrice())
                .member(member).build();
        productRepository.save(product);

        AtomicInteger counter = new AtomicInteger(1);
        List<ProductImage> productImageList = dto.getImageUrlList().stream().map(url -> ProductImage.builder()
                .url(url)
                .imageOrder(counter.getAndIncrement())
                .product(product).build()).toList();
        productImageRepository.saveAll(productImageList);

        return new CreateProductResDto(product.getId());
    }

    public ReadProductResDto<ReadProductForm> findProductList(ReadProductReqDto dto){
        PageRequest pageRequest = PageRequest.of(0,30);
        List<ReadProductForm> readProductFormList = productRepository.findProductList(dto.getProductType(), dto.getProductStatus(), pageRequest).stream()
                .map(ReadProductForm::createReadProductForm).toList();
        return new ReadProductResDto<>(readProductFormList);
    }

    public ReadProductDetailResDto findProductDetail(Long productId){
        Product product = productRepository.findProductById(productId).orElseThrow();
        return ReadProductDetailResDto.createReadProductDetailResDto(product);
    }

    public SearchProductResDto<ProductForm> searchProductList(ProductType productType){
        PageRequest pageRequest = PageRequest.of(0, 30);
        return new SearchProductResDto<>(productRepository.findByProductType(productType, pageRequest).stream().map(p->ProductForm.builder()
                .productId(p.getId())
                .productName(p.getProductName())
                .minPrice(p.getMinPrice())
                .minUnit(p.getMinUnit())
                .memberName(p.getMember().getName())
                .measurementUnit(p.getMeasurementUnit()).build()).toList());
    }

}
