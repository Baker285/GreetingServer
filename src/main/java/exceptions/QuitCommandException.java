package exceptions;

public class QuitCommandException extends RuntimeException{
    public QuitCommandException(String message){
        super(message);
    }
}
