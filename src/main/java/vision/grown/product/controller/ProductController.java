package vision.grown.product.controller;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/create")
    public CreateProductResDto createProduct(@RequestBody CreateProductReqDto createProductReqDto){
        return productService.createProduct(createProductReqDto);
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
