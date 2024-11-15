package vision.grown.funding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vision.grown.funding.dto.*;
import vision.grown.funding.service.FundingService;
import vision.grown.product.ProductType;
import vision.grown.product.dto.SearchProductResDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/funding")
public class FundingController {
    private final FundingService fundingService;

    @GetMapping
    public ReadFundingResDto<ReadFundingForm> findFundingList(@RequestBody ReadFundingReqDto dto){
        return new ReadFundingResDto<>(fundingService.findFundingList(dto));
    }
    @GetMapping("/search")
    public SearchProductResDto<ReadFundingForm> searchFundingList(@RequestParam("productType")ProductType productType){
        return new SearchProductResDto<>(fundingService.searchFundingList(productType));
    }

    @PostMapping("/create")
    public CreateFundingResDto createFunding(@RequestBody CreateFundingReqDto dto){
        return fundingService.createFunding(dto);
    }
    @GetMapping("/{fundingId}")
    public ReadFundingDetailResDto findFundingDetail(@PathVariable("fundingId") Long fundingId){
        return fundingService.findFundingDetail(fundingId);
    }
}
