package vision.grown.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vision.grown.member.dto.LoginRequestDTO;
import vision.grown.member.dto.LoginResponseDTO;
import vision.grown.member.dto.MemberDTO;
import vision.grown.member.dto.MemberInfoResDto;
import vision.grown.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;
    @Operation(
            summary = "로그인",
            description = "Email, Password를 확인하고 AccessToken 발행함.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 로그인 되었습니다."),
                    @ApiResponse(responseCode = "404", description = "이메일이 틀렸습니다."),
                    @ApiResponse(responseCode = "401", description = "비밀번호가 틀렸습니다.")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return memberService.login(loginRequestDTO);
    }

    @Operation(
            summary = "회원가입",
            description = "Body에 회원정보를 담아서 요청 시 회원 가입. 이메일 중복 확인 후 호출해야함.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적으로 회원가입 되었습니다."),
                    @ApiResponse(responseCode = "400", description = "회원가입 실패. 이메일 중복되었을 가능성 있음")
            }
    )
    @PostMapping("/new")
    public ResponseEntity<String> signIn(@RequestBody MemberDTO memberDTO){
        return memberService.signIn(memberDTO);
    }

    @Operation(
            summary = "이메일 중복 확인",
            description = "\"True: 회원가입 가능\" \"False: 회원가입 불가능\""
    )
    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> isEmailEmpty(@PathVariable String email){
        return memberService.isEmailEmpty(email);
    }

    @GetMapping("/info/{memberId}")
    public MemberInfoResDto findMemberInfo(@PathVariable("memberId") Long memberId){
        return memberService.findMemberInfo(memberId);
    }

}
