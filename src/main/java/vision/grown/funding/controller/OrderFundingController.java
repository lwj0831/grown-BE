package vision.grown.funding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vision.grown.funding.dto.*;
import vision.grown.funding.service.OrderFundingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderFunding")
public class OrderFundingController {
    private final OrderFundingService orderFundingService;

    @PostMapping("/create")
    public CreateOrderFundingResDto createOrderFunding(@RequestBody CreateOrderFundingReqDto dto, Authentication authentication){
        return orderFundingService.createOrderFunding(dto, authentication);
    }
    @GetMapping("/{memberId}")
    public ReadOrderFundingResDto<ReadOrderFundingForm> findMyOrderFunding(@PathVariable("memberId")Long memberId){
        return orderFundingService.findMyOrderFunding(memberId);
    }
}
