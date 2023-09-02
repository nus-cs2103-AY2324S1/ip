package chatbot.exceptions;

/**
 * Represents an exception that occurs in the program
 */
public class DukeException extends Exception{
    /**
     * Constructor for DukeException
     * @param message Exception message that is thrown to the user
     */
    public DukeException(String message){
        super(message);
    }

    /**
     * Returns a string representation of the message
     * @return A string containing the message
     */
    @Override
    public String toString(){
        return super.getMessage();
    }
}

