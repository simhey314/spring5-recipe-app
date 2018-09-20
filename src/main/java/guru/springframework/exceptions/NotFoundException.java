package guru.springframework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	public NotFoundException() {
	}

	public NotFoundException(final String message) {
		super(message);
	}

	public NotFoundException(final Throwable cause) {
		super(cause);
	}
}
