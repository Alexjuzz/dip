package di.customexceptions;

public class BoatNotFoundExceptions extends RuntimeException{
    public BoatNotFoundExceptions(String message){
        super(message);
    }
}
