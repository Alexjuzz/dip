package di.customexceptions;

public class BoatNotFoundException extends RuntimeException{
    public BoatNotFoundException(String message){
        super(message);
    }
}
