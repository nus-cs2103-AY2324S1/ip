package chatbot;

public class UserInputException extends Exception {

    public UserInputException(String errorMessage) {
        super(errorMessage);
    }
}