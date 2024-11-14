package vision.grown.funding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vision.grown.funding.dto.*;
import vision.grown.funding.service.FundingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/funding")
public class FundingController {
    private final FundingService fundingService;

    @GetMapping
    public ReadFundingResDto<ReadFundingForm> searchFunding(@RequestBody ReadFundingReqDto dto){
        return new ReadFundingResDto<>(fundingService.findFunding(dto));
    }

    @PostMapping
    public CreateFundingResDto createFunding(@RequestBody CreateFundingReqDto dto){
        return fundingService.createFunding(dto);
    }
    @GetMapping("{fundingId}")
    public ReadFundingDetailResDto searchFundingDetail(@PathVariable("fundingId") Long fundingId){
        return fundingService.findFundingDetail(fundingId);
    }
}
