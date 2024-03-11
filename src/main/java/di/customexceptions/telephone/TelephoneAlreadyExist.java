package di.customexceptions.telephone;

public class TelephoneAlreadyExist extends RuntimeException{
    public TelephoneAlreadyExist(String message){
        super(message);
    }
}
