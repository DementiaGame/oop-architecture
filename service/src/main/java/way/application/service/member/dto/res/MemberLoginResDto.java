package way.application.service.member.dto.res;

public record MemberLoginResDto(
	Long memberSeq,
	String accessToken,
	String refreshToken
) {

}
