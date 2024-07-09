package way.presentation.member.vo.req;

import way.application.infrastructure.member.contant.Gender;
import way.application.service.member.dto.MemberDto;

public record SignUpMemberReq(
	Integer birth,
	Gender gender,
	String nickName,
	String password
) {
	public MemberDto toMemberDto() {
		return new MemberDto(
			this.birth,
			this.gender,
			this.nickName,
			this.password
		);
	}
}
