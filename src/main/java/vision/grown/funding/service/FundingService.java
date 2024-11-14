package vision.grown.funding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.center.Center;
import vision.grown.center.repository.CenterRepository;
import vision.grown.funding.Funding;
import vision.grown.funding.FundingImage;
import vision.grown.funding.FundingStatus;
import vision.grown.funding.dto.*;
import vision.grown.funding.repository.FundingImageRepository;
import vision.grown.funding.repository.FundingRepository;
import vision.grown.product.FundingProduct;
import vision.grown.product.ProductType;
import vision.grown.product.dto.FundingProductDetailForm;
import vision.grown.product.dto.FundingProductForm;
import vision.grown.product.respository.FundingProductRepository;
import vision.grown.product.respository.OrderProductRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundingService {
    private final FundingRepository fundingRepository;
    private final CenterRepository centerRepository;
    private final FundingProductRepository fundingProductRepository;
    private final FundingImageRepository fundingImageRepository;
    private final OrderProductRepository orderProductRepository;

    public List<ReadFundingForm> findFunding(ReadFundingReqDto dto){
        PageRequest pageRequest = PageRequest.of(0, 30);
        Page<Funding> fundingPage = fundingRepository.findFundingList(dto.getCenterType(), dto.getFundingStatus(), pageRequest);
        List<Funding> fundingList = fundingPage.getContent();
        return fundingList.stream().map(ReadFundingForm::createReadFundingResDto).toList();
    }
    @Transactional
    public CreateFundingResDto createFunding(CreateFundingReqDto dto){
        Center center = Center.builder().centerName(dto.getCenterName()).centerType(dto.getCenterType()).build();
        centerRepository.save(center);

        Funding funding = Funding.builder()
                .fundingTitle(dto.getFundingTitle())
                .fundingContent(dto.getFundingContent())
                .fundingExpireDate(dto.getExpireDate())
                .fundingStatus(FundingStatus.FUND)
                .center(center).build();
        fundingRepository.save(funding);

        AtomicInteger counter = new AtomicInteger(1);
        List<FundingImage> fundingImageList = dto.getImageUrlList().stream()
                .map(url -> FundingImage.builder().url(url).imageOrder(counter.getAndIncrement()).funding(funding).build())
                .toList();
        fundingImageRepository.saveAll(fundingImageList);

        List<FundingProduct> fundingProductList = dto.getFundingProductFormList().stream()
                .map(f -> FundingProduct.builder()
                .productType(f.getProductType())
                .requiredQuantity(f.getRequiredQuantity())
                .measurementUnit(f.getMeasurementUnit())
                .funding(funding).build()).toList();
        fundingProductRepository.saveAll(fundingProductList);

        return new CreateFundingResDto(funding.getId(), funding.getFundingTitle());
    }
    public ReadFundingDetailResDto findFundingDetail(Long fundingId){
        Funding funding = fundingRepository.findById(fundingId).orElseThrow();
        Center center = funding.getCenter(); //funding 하나에 연결된 Center 하나 찾으므로 fetch join 안하고 그냥 함
        //FundingImage N -> fetch join 불가, 지연로딩 사용
        List<ImageForm> imageFormList = funding.getFundingImageList().stream().map(f -> new ImageForm(f.getImageOrder(), f.getUrl())).toList();
        //
        List<FundingProductDetailForm> fundingProductFormList = funding.getFundingProductList().stream()
                .map(fp -> {
                    int requiredQuantity = fp.getRequiredQuantity();
                    int currentQuantity = getCurrentQuantity(funding, fp.getProductType());
                    return FundingProductDetailForm.builder()
                        .productType(fp.getProductType())
                        .currentQuantity(currentQuantity)
                        .requiredQuantity(requiredQuantity)
                        .raisingRate((double) currentQuantity / requiredQuantity * 100)
                        .build();
                }).toList();

        return ReadFundingDetailResDto.builder().centerType(center.getCenterType())
                .centerName(center.getCenterName())
                .fundingTitle(funding.getFundingTitle())
                .fundingContent(funding.getFundingContent())
                .fundingRate(funding.getFundingRate())
                .imageList(imageFormList)
                .expireDate(funding.getFundingExpireDate())
                .fundingProductList(fundingProductFormList).build();
    }
    public int getCurrentQuantity(Funding funding, ProductType productType){
        return funding.getOrderFundingList().stream()
                .mapToInt(of -> orderProductRepository.getCurrentQuantity(of.getId(), productType)).sum();
    }
}
