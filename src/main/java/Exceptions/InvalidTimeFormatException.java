package Exceptions;
/**
 * Custom exception class to handle cases where input date and time is in invalid format.
 */
public class InvalidTimeFormatException extends Exception{
    public InvalidTimeFormatException(String message) {
        super("Macho! The input time format of a " + message + " is wrong, macho! Please enter in format " +
                "yyyy-MM-dd HH:mm, macho!");
    }
}
