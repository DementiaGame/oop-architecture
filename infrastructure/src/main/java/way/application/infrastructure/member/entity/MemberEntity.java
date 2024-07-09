package way.application.infrastructure.member.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import way.application.infrastructure.member.contant.Gender;

@Entity
@Table(name = "MEMBER")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Builder(toBuilder = true)
public class MemberEntity {
	@Id
	@Column(name = "member_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberSeq;

	@Column(name = "birth", nullable = false)
	private Integer birth;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "nick_name", nullable = false)
	private String nickName;

	@Column(name = "password", nullable = false)
	private String password;
}
