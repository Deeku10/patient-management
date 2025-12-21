package in.bydeepak.patientservice.exception;

public class EmailAlreadyAvailableException extends RuntimeException {
    public EmailAlreadyAvailableException(String message) {
        super(message);
    }
}
