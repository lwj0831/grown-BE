package vision.grown.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vision.grown.product.ProductType;
import vision.grown.product.dto.*;
import vision.grown.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/search")
    public SearchProductResDto<ReadProductForm> searchProductList(@RequestParam("productType") ProductType productType){
        return productService.searchProductList(productType);
    }

    @Operation(
            summary = "상품 판매 등록"
    )
    @PostMapping("/create")
    public CreateProductResDto createProduct(@ModelAttribute CreateProductReqDto createProductReqDto, Authentication authentication){
        return productService.createProduct(createProductReqDto, authentication);
    }

    @GetMapping
    public ReadProductResDto<ReadProductForm> findProductList(@RequestBody ReadProductReqDto dto){
        return productService.findProductList(dto);
    }

    @GetMapping("/{productId}")
    public ReadProductDetailResDto findProductDetail(@PathVariable("productId")Long productId){
        return productService.findProductDetail(productId);
    }

    @GetMapping("/cheapest")
    public ReadProductResDto<ReadProductForm> findCheapestProductList(){
        return productService.findCheapestProductList();
    }
}
