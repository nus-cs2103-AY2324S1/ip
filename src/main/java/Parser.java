import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    
    public static Task parseTask(String input) throws DukdukException {
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new DukdukException("OOPS!!! The description cannot be empty.");
            }
            return new ToDo(input.substring(5));
        } else if (input.startsWith("deadline")) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new DukdukException("OOPS!!! The deadline format is incorrect. " +
                        "Use '/by' to specify the deadline.");
            }
            String description = input.substring(9, byIndex).trim();
            String byString = input.substring(byIndex + 3).trim();
            try {
                LocalDateTime by = LocalDateTime.parse(byString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new DukdukException("OOPS!!! The date/time format is incorrect. " +
                        "Please use 'yyyy-MM-dd HHmm' format.");
            }
        } else if (input.startsWith("event")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new DukdukException("OOPS!!! The event format is incorrect. Use '/from' " +
                        "and '/to' to specify the timings.");
            }
            String description = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            try {
                LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                if (!toDateTime.isAfter(fromDateTime)) {
                    throw new DukdukException("OOPS!!! The 'to' date/time must be " +
                            "after the 'from' date/time.");
                }
                return new Event(description, fromDateTime, toDateTime);
            } catch (DateTimeParseException e) {
                throw new DukdukException("OOPS!!! The date/time format is incorrect. " +
                        "Please use 'yyyy-MM-dd HHmm' format.");
            }
        } else {
            throw new DukdukException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
