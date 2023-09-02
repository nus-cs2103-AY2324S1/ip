package bot.parsers;

import bot.exception.DateTimeParseBotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatetimeParser {

    public static LocalDateTime parseTimeInput(String str) throws DateTimeParseBotException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseBotException("Please write your dateTime as d/MM/yyyy HH:mm");
        }
    }

    public static String reformatTimeOutput(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        return time.format(formatter);
    }

    public static LocalDateTime convertToLocalDateTime(String str) throws DateTimeParseBotException {
        try {
            return LocalDateTime.parse(str);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseBotException("The dateTime format is corrupted " +
                    "in the ./data/bot.task.txt.");
        }
    }
}
