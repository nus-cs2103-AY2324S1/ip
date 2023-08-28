package exception;

public class InvalidInputException extends Exception{
    @Override
    public String toString() {
        return "You given input or command is invalid. Returning to homepage...";
    }
}
