package util;

import command.CommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {

    /**
     * Parse a string into a LocalDateTime instance
     * @param str The string representing a time in the format "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss"
     * @return The LocalDateTime instance created from the String
     * @throws CommandException when the string isn't in the correct format
     */
    public static LocalDateTime parseDateTimeString(String str) throws CommandException {
        LocalDateTime retVal;
        try {
            retVal = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // Date + Time
        }catch (DateTimeParseException e){
            try{
                retVal = LocalDateTime.parse(str + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // Date only
            }
            catch (DateTimeParseException e2){
                throw new CommandException("Sorry, I couldn't recognize as a valid date-time: " + str);
            }
        }
        return retVal;
    }

    /**
     * Returns a formatted string representing a LocalDateTime instance
     * @param dt the LocalDateTime instance
     * @return the formatted string representing the LocalDateTime instance
     */
    public static String dateTimeToString(LocalDateTime dt){
        return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
