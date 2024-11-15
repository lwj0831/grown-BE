package vision.grown.funding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @GetMapping("/deadline")
    public ReadFundingResDto<ReadFundingForm> findDeadLineFunding(){
        return new ReadFundingResDto<>(fundingService.findDeadLineFunding());
    }

    @PostMapping(value = "/create",consumes = "multipart/form-data")
    public CreateFundingResDto createFunding(@ModelAttribute CreateFundingReqDto dto){
        List<MultipartFile> imageList = dto.getImageList();
        for (MultipartFile multipartFile : imageList) {
            System.out.println(multipartFile);
        }
        return fundingService.createFunding(dto);
    }
    @GetMapping("/{fundingId}")
    public ReadFundingDetailResDto findFundingDetail(@PathVariable("fundingId") Long fundingId){
        return fundingService.findFundingDetail(fundingId);
    }
}
