package way.application.utils.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {

	// BAD_REQUEST
	DTO_BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "DTO Bad Request Exception", "DB001"),
	MEMBER_SEQ_BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "Member Seq Bad Request Exception", "MSB002"),

	// NOT_FOUND
	MEMBER_NICK_NAME_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "Member Nick Name Not Found Exception", "MNMN001"),

	// CONFLICT
	NICK_NAME_ALREADY_EXIST_CONFLICT_EXCEPTION(HttpStatus.CONFLICT, "Nick Name Already Exist Conflict Exception", "NNAEC001"),

	// SERVER
	UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Exception", "S500"),
	FIREBASE_CLOUD_MESSAGING_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "FIREBASE_CLOUD_MESSAGING_EXCEPTION", "S501"),
	;

	private final HttpStatus httpStatus;
	private final String message;
	private final String code;
}
