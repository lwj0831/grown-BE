package vision.grown.funding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.center.repository.CenterRepository;
import vision.grown.funding.Funding;
import vision.grown.funding.OrderFunding;
import vision.grown.funding.repository.FundingRepository;
import vision.grown.member.Member;
import vision.grown.member.repository.MemberRepository;
import vision.grown.funding.dto.CreateOrderFundingReqDto;
import vision.grown.funding.dto.CreateOrderFundingResDto;
import vision.grown.funding.repository.OrderFundingRepository;
import vision.grown.product.OrderProduct;
import vision.grown.product.Product;
import vision.grown.product.dto.OrderProductForm;
import vision.grown.product.respository.OrderProductRepository;
import vision.grown.product.respository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    @Transactional
    public CreateOrderFundingResDto createOrderFunding(CreateOrderFundingReqDto dto){
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        Funding funding = fundingRepository.findById(dto.getFundingId()).orElseThrow();

        OrderFunding orderFunding = OrderFunding.createOrderFunding(member,funding);

        List<Product> products = new ArrayList<>();
        List<OrderProduct> orderProducts = dto.getOrderProductFormList().stream()
                .map(d -> {
                    Product product = productRepository.findById(d.getProductId()).orElseThrow();
                    products.add(product); // Product 리스트에 추가
                    return createOrderProduct(d.getOrderQuantity(), d.getOrderPrice(), product, orderFunding);
                })
                .toList();
        orderProductRepository.saveAll(orderProducts);

        orderFundingRepository.save(orderFunding);

        List<String> memberNameList = products.stream().map(p -> p.getMember().getName()).toList();
        double contributionRate = (double) orderFunding.getTotalOrderQuantity()/funding.getTotalRequiredAmount()*100;
        contributionRate = Math.round(contributionRate * 100.0) / 100.0;
        return CreateOrderFundingResDto.builder()
                .centerName(funding.getCenter().getCenterName())
                .totalOrderPrice(orderFunding.getTotalOrderPrice())
                .memberNameList(memberNameList)
                .contributionRate(contributionRate)
                .build();
    }

}
