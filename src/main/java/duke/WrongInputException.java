package duke;

public class WrongInputException extends Exception{
    /**
     * Gets String representation of the Exception.
     *
     * @return String representation of the Exception.
     */
    public String toString() {
        return "Wrong input format. Can type properly anot?";
    }
}
