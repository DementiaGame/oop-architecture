package way.application.domain.member;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import way.application.infrastructure.member.entity.MemberEntity;

@Component
@RequiredArgsConstructor
public class MemberDomain {
	private final RedisTemplate<String, Object> redisTemplate;

	public void saveTokenInRedis(String nickName, String accessToken, String refreshToken) {
		// AccessToken 저장: 2시간으로 설정
		redisTemplate.opsForValue().set(nickName, accessToken, Duration.ofHours(2));

		// RefreshToken 저장: 3시간으로 설정
		redisTemplate.opsForValue().set(nickName, refreshToken, Duration.ofHours(3));
	}
}
