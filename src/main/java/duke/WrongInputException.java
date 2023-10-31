package duke;

/**
 * This class is used to represent an exception thrown when a Wrong Input is given
 */
public class WrongInputException extends Exception {

    /**
     * Constructor for creating a WrongInputTask exception
     * @param taskExceptionMessage What is the error or invalid format
     * @param solution  The recommended solution
     */
    public WrongInputException(String taskExceptionMessage, String solution) {
        super("Invalid Format: " + taskExceptionMessage + "\nRecommendation: " + solution);
    }
}
