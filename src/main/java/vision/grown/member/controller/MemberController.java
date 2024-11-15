package vision.grown.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vision.grown.member.dto.*;
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

    @Operation(
            summary = "아이디(이메일) 찾기",
            description = "Name과 PhoneNum을 이용해 아이디 찾기 - 못 찾으면 Not Found"
    )
    @GetMapping("/find/id")
    public ResponseEntity<FindIdResponseDTO> findMemberId(@RequestBody FindIdRequestDTO dto){
        return memberService.findMemberId(dto);
    }

    @Operation(
            summary = "비밀번호 변경",
            description = "email에 해당하는 유저의 비밀번호 변경 - email에 해당하는 유저 없으면 Not Found"
    )
    @PutMapping("/change/password")
    public ResponseEntity<ChangePasswordResDTO> changePassword(@RequestBody ChangePasswordReqDTO dto){
        return memberService.changePassword(dto);
    }
}
