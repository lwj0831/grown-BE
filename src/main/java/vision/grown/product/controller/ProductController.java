package vision.grown.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import vision.grown.product.ProductType;
import vision.grown.product.dto.*;
import vision.grown.product.respository.ProductRepository;
import vision.grown.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    @GetMapping("/{productType}")
    public FindProductResDto<ProductForm> searchProduct(@PathVariable("productType") ProductType productType){
        PageRequest pageRequest = PageRequest.of(0, 30);
        return new FindProductResDto<>(productRepository.findByProductType(productType, pageRequest).stream().map(p->ProductForm.builder()
                .productId(p.getId())
                .productName(p.getProductName())
                .minPrice(p.getMinPrice())
                .minUnit(p.getMinUnit())
                .memberName(p.getMember().getName())
                .measurementUnit(p.getMeasurementUnit()).build()).toList());
    }

    @PostMapping
    public CreateProductResDto createProduct(@RequestBody CreateProductReqDto createProductReqDto){
        return productService.createProduct(createProductReqDto);
    }

    @GetMapping
    public ReadProductResDto<ReadProductForm> findProduct(@RequestBody ReadProductReqDto dto){
        return productService.findProduct(dto);
    }
}
