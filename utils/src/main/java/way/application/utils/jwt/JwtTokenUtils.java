package way.application.utils.jwt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

	private final RedisTemplate<String, String> redisTemplate;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.accessTokenExpiration}")
	private long accessTokenExpiration;

	@Value("${jwt.refreshTokenExpiration}")
	private long refreshTokenExpiration;

	public String generateAccessToken(String userId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + accessTokenExpiration);

		return Jwts.builder()
			.setSubject(userId)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String generateRefreshToken(String userId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + refreshTokenExpiration);

		return Jwts.builder()
			.setSubject(userId)
			.setIssuedAt(now)
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String extractToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		String token = "";

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7); // Remove "Bearer " from the header value
		}
		return token;
	}

	public String extractUserId(String token) {

		Claims claims = Jwts.parser()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(token)
			.getBody();

		return claims.getSubject();
	}

}
