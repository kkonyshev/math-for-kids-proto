package mfk.domain;

public class ProfileNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = -7034511108730056962L;
	public ProfileNotFoundException(String message) {
		super(message);
	}
}
