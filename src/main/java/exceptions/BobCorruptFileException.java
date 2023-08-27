package exceptions;

public class BobCorruptFileException extends BobException{
    public BobCorruptFileException(String errorMessage) {
        super(errorMessage);
    }
}
