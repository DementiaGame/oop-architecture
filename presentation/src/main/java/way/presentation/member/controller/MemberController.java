package way.presentation.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import way.application.service.member.dto.res.MemberLoginResDto;
import way.application.service.member.service.MemberService;
import way.presentation.base.BaseResponse;
import way.presentation.member.validate.SignUpMemberReqValidator;
import way.presentation.member.vo.req.LoginMemberReq;
import way.presentation.member.vo.req.SignUpMemberReq;
import way.presentation.member.vo.res.LoginMemberRes;
import way.presentation.member.vo.res.SignUpMemberRes;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final SignUpMemberReqValidator signUpMemberReqValidator;

	private final MemberService memberService;

	@PostMapping("/sign-up")
	public ResponseEntity<BaseResponse<SignUpMemberRes>> signUpMember(@RequestBody SignUpMemberReq request) {
		// Request 유효성 검사
		signUpMemberReqValidator.validate(request);

		// 의존 관계 역전을 위한 VO -> DTO 변환 후 인자 전달
		Long memberSeq = memberService.signUpMemberEntity(request.toMemberDto());

		return ResponseEntity.ok().body(
			BaseResponse.ofSuccess(HttpStatus.CREATED.value(), new SignUpMemberRes(memberSeq))
		);
	}

	@PostMapping("/login")
	public ResponseEntity<BaseResponse<LoginMemberRes>> loginMember(@RequestBody LoginMemberReq request) {
		// VO -> DTO 변환
		MemberLoginResDto memberLoginResDto = memberService.loginMemberEntity(request.toMemberLoginReqDto());

		// DTO -> VO 변환
		LoginMemberRes response = new LoginMemberRes(
			memberLoginResDto.memberSeq(),
			memberLoginResDto.accessToken(),
			memberLoginResDto.refreshToken()
		);

		return ResponseEntity.ok().body(BaseResponse.ofSuccess(HttpStatus.OK.value(), response));
	}
}
