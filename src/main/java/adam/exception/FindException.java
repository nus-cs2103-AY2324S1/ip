package adam.exception;

/**
 * This exception is thrown when user inputs find without specifying anything.
 */
public class FindException extends AdamException{
    @Override
    public String getInfo() {
        return "OOPS!!! You need  to add a word that you are trying to find";
    }
}
