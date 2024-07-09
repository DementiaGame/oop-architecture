package way.presentation.member.vo.res;

public record LoginMemberRes(
	Long memberSeq,
	String accessToken,
	String refreshToken
) {

}
