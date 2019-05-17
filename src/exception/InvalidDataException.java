package exception;

import java.security.spec.ECField;

public class InvalidDataException extends Exception {

    public InvalidDataException(String message){
        super(message);
    }

    public InvalidDataException(Exception exception, String message){
        super(message, exception);
    }
}
