package util;

import command.CommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
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

    public static String dateTimeToString(LocalDateTime dt){
        return dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
