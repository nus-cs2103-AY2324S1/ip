package duke.exception;

/**
 * Represents an Invalid Date Exception
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class InvalidDateException extends Exception {
    /**
     * Overrides the Exception Object's toString method and contains an error message.
     * @return The error messsage String with a correction of Date input format.
     */
    @Override
    public String toString() {
        return "     Yoho! The date provided is invalid. " +
                "Ensure all dates are of the format YYYY-MM-DD. Thanks!";
    }
}
