package way.application.service.member.dto;

import way.application.infrastructure.member.contant.Gender;

public record MemberDto(
	Integer birth,
	Gender gender,
	String nickName,
	String password
) {

}
