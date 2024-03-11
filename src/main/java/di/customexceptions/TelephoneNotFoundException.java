package di.customexceptions;

public class TelephoneNotFoundException extends RuntimeException {
    public TelephoneNotFoundException(String message){
        super(message);
    }
}
