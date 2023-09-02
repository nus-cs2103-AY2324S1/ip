package Exceptions;

/**
 * An Exception to be thrown when the command is unrecognizable by the chatbot.
 *
 * @author Anton Tan Hong Zhi
 */
public class UnknownCommandException extends Exception {

    /**
     * Returns an error message.
     * @return a basic error message
     */
    public String toString(){
        return "I have no idea what that means";
    }
}
