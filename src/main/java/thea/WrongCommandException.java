package thea;

public class WrongCommandException extends Exception{
    public WrongCommandException(String errorMessage) {
        super(errorMessage);
    }
}
