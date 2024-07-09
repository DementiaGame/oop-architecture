package way.application.infrastructure.member.repository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import way.application.infrastructure.member.entity.MemberEntity;
import way.application.utils.exception.BadRequestException;
import way.application.utils.exception.ConflictException;
import way.application.utils.exception.ErrorResult;

@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
	private final MemberJpaRepository memberJpaRepository;

	@Override
	public void validateMemberNickName(String nickName) {
		if (memberJpaRepository.findByNickName(nickName).isPresent()) {
			throw new ConflictException(ErrorResult.NICK_NAME_ALREADY_EXIST_CONFLICT_EXCEPTION);
		}
	}

	@Override
	public MemberEntity saveMemberEntity(MemberEntity memberEntity) {
		return memberJpaRepository.save(memberEntity);
	}

	@Override
	public MemberEntity findByMemberNickName(String nickName) {
		return memberJpaRepository.findByNickName(nickName)
			.orElseThrow(() -> new BadRequestException(ErrorResult.MEMBER_NICK_NAME_NOT_FOUND_EXCEPTION));
	}
}
