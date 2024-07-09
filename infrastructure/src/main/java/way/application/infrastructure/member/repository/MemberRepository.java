package way.application.infrastructure.member.repository;

import way.application.infrastructure.member.entity.MemberEntity;

public interface MemberRepository {
	void validateMemberNickName(String nickName);
	MemberEntity saveMemberEntity(MemberEntity memberEntity);
	MemberEntity findByMemberNickName(String nickName);
}
