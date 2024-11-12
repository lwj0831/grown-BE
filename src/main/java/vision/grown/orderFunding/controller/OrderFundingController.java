package vision.grown.orderFunding.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vision.grown.orderFunding.dto.CreateOrderFundingReqDto;
import vision.grown.orderFunding.dto.CreateOrderFundingResDto;
import vision.grown.orderFunding.service.OrderFundingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderFunding")
public class OrderFundingController {
    private final OrderFundingService orderFundingService;

    @PostMapping
    public CreateOrderFundingResDto createOrderFunding(@RequestBody CreateOrderFundingReqDto dto){
        return orderFundingService.createFunding(dto);
    }
}
