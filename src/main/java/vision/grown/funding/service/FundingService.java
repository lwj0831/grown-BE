package vision.grown.funding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vision.grown.funding.Funding;
import vision.grown.funding.dto.ReadFundingReqDto;
import vision.grown.funding.dto.ReadFundingResDto;
import vision.grown.funding.repository.FundingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FundingService {
    private final FundingRepository fundingRepository;

    public List<ReadFundingResDto> findFunding(ReadFundingReqDto dto){
        PageRequest pageRequest = PageRequest.of(0, 30);
        Page<Funding> fundingPage = fundingRepository.findFundingList(dto.getCenterType(), dto.getFundingStatus(), pageRequest);
        List<Funding> fundingList = fundingPage.getContent();
        return fundingList.stream().map(ReadFundingResDto::createReadFundingResDto).toList();
    }
}
