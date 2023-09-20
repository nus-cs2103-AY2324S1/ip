package chatbot.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(){
        super("OOPS!!! Please type a valid command!");
    }
}
