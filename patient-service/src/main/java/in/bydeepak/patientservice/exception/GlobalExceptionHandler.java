package in.bydeepak.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {map.put(error.getField(),error.getDefaultMessage());});
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(EmailAlreadyAvailableException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyAvailableException(EmailAlreadyAvailableException ex) {
        log.warn("An user with this email already exists {}",ex.getMessage());
        Map<String,String> map = new HashMap<>();
        map.put("message","Email already available");
        return ResponseEntity.badRequest().body(map);
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String,String>> handlePatientNotFoundException(PatientNotFoundException ex) {
        log.warn("An patient with this id not found {}",ex.getMessage());
        Map<String,String> map = new HashMap<>();
        map.put("message","Patient not found");
        return ResponseEntity.badRequest().body(map);
    }
}
