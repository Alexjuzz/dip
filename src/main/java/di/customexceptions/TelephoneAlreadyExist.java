package di.customexceptions;

public class TelephoneAlreadyExist extends RuntimeException{
    public TelephoneAlreadyExist(String message){
        super(message);
    }
}
