package vision.grown.member.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vision.grown.funding.OrderFunding;
import vision.grown.member.Member;
import vision.grown.member.dto.*;
import vision.grown.member.jwt.JwtTokenProvider;
import vision.grown.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        //AccessToken 만들어서 줘야함
        Optional<Member> byEmail = memberRepository.findByEmail(loginRequestDTO.getEmail());
        if (byEmail.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!passwordEncoder.matches(loginRequestDTO.getPassword(), byEmail.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder().
                    accessToken(jwtTokenProvider.createAccessToken(loginRequestDTO.getEmail()))
                    .build();
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> signIn(MemberDTO memberDTO) {
        try {
            // 비밀번호 인코딩
            String encoded = passwordEncoder.encode(memberDTO.getPassword());

            // 회원 저장
            Member saved = memberRepository.save(Member.builder()
                    .name(memberDTO.getName())
                    .email(memberDTO.getEmail())
                    .password(encoded)
                    .phoneNum(memberDTO.getPhoneNumber())
                    .build());

            // 저장된 객체가 null인 경우 처리
            return new ResponseEntity<>("회원가입 완료", HttpStatus.OK); // 성공 시 200 반환
        } catch (Exception e) {
            // 예외 발생 시 처리
            log.info("회원가입 중 오류: " + e);
            return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST); // 이메일 조회 후 회원가입 할것
        }
    }

    public ResponseEntity<Boolean> isEmailEmpty(String email) {
        return new ResponseEntity<>(memberRepository.findByEmail(email).isEmpty(), HttpStatus.OK);
    }

    public Optional<Member> checkPermission(Authentication authentication){
        String email = authentication.getName();
        return memberRepository.findByEmail(email);
    }

    public ResponseEntity<MemberInfoResDto> findMemberInfo(Authentication authentication){
        Optional<Member> member = checkPermission(authentication);
        if (member.isEmpty()){
            return new ResponseEntity<>(MemberInfoResDto.builder().build(), HttpStatus.BAD_REQUEST);
        }
        Member validMember = member.get();
        int memberFundingPrice = validMember.getOrderFundingList().stream().mapToInt(OrderFunding::getOrderFundingPrice).sum();
        return new ResponseEntity<>(MemberInfoResDto.builder()
                .memberId(validMember.getId())
                .name(validMember.getName())
                .phoneNum(validMember.getPhoneNum())
                .memberFundingPrice(memberFundingPrice)
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<FindIdResponseDTO> findMemberId(FindIdRequestDTO dto){
        Optional<Member> member = memberRepository.findMemberByNameAndPhoneNum(dto.getName(), dto.getPhoneNum());
        if (member.isEmpty()){
            return new ResponseEntity<>(FindIdResponseDTO.builder().build(), HttpStatus.NOT_FOUND);
        }
        Member validMember = member.get();
        return new ResponseEntity<>(FindIdResponseDTO.builder().email(validMember.getEmail()).build(), HttpStatus.OK);
    }

    public ResponseEntity<ChangePasswordResDTO> changePassword(ChangePasswordReqDTO dto){
        Optional<Member> member = memberRepository.findByEmail(dto.getEmail());
        if (member.isEmpty()){
            return new ResponseEntity<>(ChangePasswordResDTO.builder().build(), HttpStatus.NOT_FOUND);
        }
        Member validMember = member.get();
        String encoded = passwordEncoder.encode(dto.getPassword());
        validMember.updatePassword(encoded);
        memberRepository.save(validMember);
        return new ResponseEntity<>(ChangePasswordResDTO.builder().name(validMember.getName()).message("비밀번호 변경 성공").build(), HttpStatus.OK);
    }
}
