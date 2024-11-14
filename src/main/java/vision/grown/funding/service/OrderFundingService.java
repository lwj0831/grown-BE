package vision.grown.funding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.funding.Funding;
import vision.grown.funding.FundingStatus;
import vision.grown.funding.OrderFunding;
import vision.grown.funding.repository.FundingRepository;
import vision.grown.member.Member;
import vision.grown.member.repository.MemberRepository;
import vision.grown.funding.dto.CreateOrderFundingReqDto;
import vision.grown.funding.dto.CreateOrderFundingResDto;
import vision.grown.funding.repository.OrderFundingRepository;
import vision.grown.product.OrderProduct;
import vision.grown.product.Product;
import vision.grown.product.respository.FundingProductRepository;
import vision.grown.product.respository.OrderProductRepository;
import vision.grown.product.respository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static vision.grown.product.OrderProduct.createOrderProduct;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderFundingService {
    private final OrderFundingRepository orderFundingRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final FundingRepository fundingRepository;
    private final OrderProductRepository orderProductRepository;
    private final FundingProductRepository fundingProductRepository;

    @Transactional
    public CreateOrderFundingResDto createOrderFunding(CreateOrderFundingReqDto dto){
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Funding funding = fundingRepository.findById(dto.getFundingId()).orElseThrow();

        OrderFunding orderFunding = OrderFunding.createOrderFunding(member,funding);

        List<Product> products = new ArrayList<>(); //기부처 이름 응답 보낼 때 사용
        List<OrderProduct> orderProducts = dto.getOrderProductFormList().stream()
                .map(d -> {
                    Product product = productRepository.findById(d.getProductId()).orElseThrow();
                    products.add(product); // Product 리스트에 추가
                    return createOrderProduct(d.getOrderQuantity(), d.getOrderPrice(), product, orderFunding);
                })
                .toList();
        orderProductRepository.saveAll(orderProducts);

        orderFundingRepository.save(orderFunding);
        //펀딩 달성률 체크 후 상태 업데이트
        updateFundingStatus(products, funding);

        List<String> memberNameList = products.stream().map(p -> p.getMember().getName()).distinct().toList();
        double contributionRate = (double) orderFunding.getOrderFundingQuantity()/funding.getTotalRequiredQuantity()*100;
        contributionRate = Math.floor(contributionRate * 100.0) / 100.0;
        return CreateOrderFundingResDto.builder()
                .centerName(funding.getCenter().getCenterName())
                .totalOrderPrice(orderFunding.getOrderFundingPrice())
                .memberNameList(memberNameList)
                .contributionRate(contributionRate)
                .build();
    }

    private void updateFundingStatus(List<Product> products, Funding funding) {
        boolean check = true;
        for (Product product : products) {
            int currentQuantity = funding.getOrderFundingList().stream()
                    .mapToInt(of -> orderProductRepository.getCurrentQuantity(of.getId(), product.getProductType())).sum();
            int requiredQuantity = fundingProductRepository.getRequiredQuantity(funding.getId(), product.getProductType());
            if(currentQuantity < requiredQuantity){
                check = false;
            }
        }
        if (check) funding.setFundingStatus(FundingStatus.COMP);

    }

}
