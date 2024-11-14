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
import vision.grown.funding.dto.CreateFundingReqDto;
import vision.grown.funding.dto.CreateFundingResDto;
import vision.grown.funding.dto.ReadFundingReqDto;
import vision.grown.funding.dto.ReadFundingResDto;
import vision.grown.funding.repository.FundingImageRepository;
import vision.grown.funding.repository.FundingRepository;
import vision.grown.product.FundingProduct;
import vision.grown.product.respository.FundingProductRepository;

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

    public List<ReadFundingResDto> findFunding(ReadFundingReqDto dto){
        PageRequest pageRequest = PageRequest.of(0, 30);
        Page<Funding> fundingPage = fundingRepository.findFundingList(dto.getCenterType(), dto.getFundingStatus(), pageRequest);
        List<Funding> fundingList = fundingPage.getContent();
        return fundingList.stream().map(ReadFundingResDto::createReadFundingResDto).toList();
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
}
