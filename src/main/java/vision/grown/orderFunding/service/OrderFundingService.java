package vision.grown.orderFunding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.funding.Funding;
import vision.grown.funding.repository.FundingRepository;
import vision.grown.member.Member;
import vision.grown.member.repository.MemberRepository;
import vision.grown.orderFunding.OrderFunding;
import vision.grown.orderFunding.dto.CreateOrderFundingReqDto;
import vision.grown.orderFunding.dto.CreateOrderFundingResDto;
import vision.grown.orderFunding.repository.OrderFundingRepository;
import vision.grown.product.Product;
import vision.grown.product.respository.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderFundingService {
    private final OrderFundingRepository orderFundingRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final FundingRepository fundingRepository;

    @Transactional
    public CreateOrderFundingResDto createFunding(CreateOrderFundingReqDto dto){
        Product product = productRepository.findById(dto.getProductId()).orElseThrow();
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Funding funding = fundingRepository.findById(dto.getFundingId()).orElseThrow();

        OrderFunding orderFunding = OrderFunding.createOrderFunding(dto.getQuantity(), member, product, funding);
        orderFundingRepository.save(orderFunding);

        return CreateOrderFundingResDto.builder()
                .quantity(dto.getQuantity())
                .productType(product.getProductType())
                .orderPrice(orderFunding.getOrderPrice())
                .build();
    }

}
