package way.application.service.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import way.application.infrastructure.member.entity.MemberEntity;
import way.application.infrastructure.member.repository.MemberRepository;
import way.application.service.member.dto.MemberDto;
import way.application.service.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	private final MemberMapper memberMapper;

	@Transactional
	public Long signUpMemberEntity(MemberDto memberDto) {
		// Nick Name 유효성 검사
		memberRepository.validateMemberNickName(memberDto.nickName());

		// Member 저장
		MemberEntity savedMemberEntity = memberRepository.saveMemberEntity(memberMapper.toMemberEntity(memberDto));

		return savedMemberEntity.getMemberSeq();
	}
}
