package way.presentation.member.validate;

import org.springframework.stereotype.Component;

import way.application.infrastructure.member.contant.Gender;
import way.application.utils.exception.BadRequestException;
import way.application.utils.exception.ErrorResult;
import way.presentation.member.vo.req.SignUpMemberReq;

@Component
public class SignUpMemberReqValidator {
	public void validate(SignUpMemberReq request) {
		validateBirth(request.birth());
		validateGender(request.gender());
		validateNickName(request.nickName());
		validatePassword(request.password());
	}

	private void validateBirth(Integer birth) {
		if (birth == null || birth <= 0) {
			throw new BadRequestException(ErrorResult.DTO_BAD_REQUEST_EXCEPTION);
		}
	}

	private void validateGender(Gender gender) {
		if (gender == null) {
			throw new BadRequestException(ErrorResult.DTO_BAD_REQUEST_EXCEPTION);
		}
	}

	private void validateNickName(String nickName) {
		if (nickName == null || nickName.isEmpty()) {
			throw new BadRequestException(ErrorResult.DTO_BAD_REQUEST_EXCEPTION);
		}
	}

	private void validatePassword(String password) {
		if (password == null || password.length() < 6) {
			throw new BadRequestException(ErrorResult.DTO_BAD_REQUEST_EXCEPTION);
		}
	}
}
