package Bob.exception;

public class BobCorruptFileException extends BobException{
    public BobCorruptFileException(String errorMessage) {
        super("Your file may be corrupted :(");
    }
}
