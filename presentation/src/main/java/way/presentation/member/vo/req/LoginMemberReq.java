package way.presentation.member.vo.req;

import way.application.service.member.dto.req.MemberLoginReqDto;

public record LoginMemberReq(
	String nickName,
	String password
) {
	public MemberLoginReqDto toMemberLoginReqDto() {
		return new MemberLoginReqDto(
			this.nickName,
			this.password
		);
	}
}
