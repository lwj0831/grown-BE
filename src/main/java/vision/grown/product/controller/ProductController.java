package vision.grown.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vision.grown.product.ProductType;
import vision.grown.product.dto.ProductForm;
import vision.grown.product.dto.ReadProductResDto;
import vision.grown.product.respository.ProductRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductRepository productRepository;
    @GetMapping
    public ReadProductResDto<ProductForm> searchProduct(@RequestParam("productType") ProductType productType){
        PageRequest pageRequest = PageRequest.of(0, 30);
        return new ReadProductResDto<>(productRepository.findByProductType(productType, pageRequest).stream().map(p->ProductForm.builder()
                .productId(p.getId())
                .productName(p.getProductName())
                .minPrice(p.getMinPrice())
                .minUnit(p.getMinUnit())
                .memberName(p.getMember().getName())
                .measurementUnit(p.getMeasurementUnit()).build()).toList());
    }
}
