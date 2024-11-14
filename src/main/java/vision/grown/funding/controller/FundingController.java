package vision.grown.funding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vision.grown.funding.dto.CreateFundingReqDto;
import vision.grown.funding.dto.CreateFundingResDto;
import vision.grown.funding.dto.ReadFundingReqDto;
import vision.grown.funding.dto.ReadFundingResDto;
import vision.grown.funding.service.FundingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/funding")
public class FundingController {
    private final FundingService fundingService;

    @GetMapping
    public List<ReadFundingResDto> searchFunding(@RequestBody ReadFundingReqDto dto){
        return fundingService.findFunding(dto);
    }

    @PostMapping
    public CreateFundingResDto createFunding(@RequestBody CreateFundingReqDto dto){
        return fundingService.createFunding(dto);
    }
}
