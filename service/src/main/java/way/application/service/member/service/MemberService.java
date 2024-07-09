package way.application.service.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import way.application.domain.member.MemberDomain;
import way.application.infrastructure.member.entity.MemberEntity;
import way.application.infrastructure.member.repository.MemberRepository;
import way.application.service.member.dto.MemberDto;
import way.application.service.member.dto.req.MemberLoginReqDto;
import way.application.service.member.dto.res.MemberLoginResDto;
import way.application.service.member.mapper.MemberMapper;
import way.application.utils.jwt.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final JwtTokenUtils jwtTokenUtils;

	private final MemberMapper memberMapper;

	private final MemberDomain memberDomain;

	@Transactional
	public Long signUpMemberEntity(MemberDto memberDto) {
		// Nick Name 유효성 검사
		memberRepository.validateMemberNickName(memberDto.nickName());

		// Member 저장
		MemberEntity savedMemberEntity = memberRepository.saveMemberEntity(memberMapper.toMemberEntity(memberDto));

		return savedMemberEntity.getMemberSeq();
	}

	@Transactional
	public MemberLoginResDto loginMemberEntity(MemberLoginReqDto memberLoginReqDto) {
		// Nick Name 유효성 검사
		MemberEntity memberEntity = memberRepository.findByMemberNickName(memberLoginReqDto.nickName());

		// Token 발급 (Nick Name이 Unique 하므로 Nick Name으로 발급)
		String accessToken = jwtTokenUtils.generateAccessToken(memberEntity.getNickName());
		String refreshToken = jwtTokenUtils.generateRefreshToken(memberEntity.getNickName());

		// Service 계층에서 Redis 저장이 아닌 Domain 에서 책임 부여
		memberDomain.saveTokenInRedis(memberEntity.getNickName(), accessToken, refreshToken);

		return new MemberLoginResDto(memberEntity.getMemberSeq(), accessToken, refreshToken);
	}
}
