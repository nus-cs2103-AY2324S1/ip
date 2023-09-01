package smolbrain.exception;

public class InvalidCommandException extends Exception{

    public InvalidCommandException() {
        super();
    }

    @Override
    public String toString() {
        return "I'm sorry, but I don't know what that means :-(";
    }

}