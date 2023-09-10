package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to represent the date and time of a task.
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Constructor for creating a DateTime object
     *
     * @param dateTime the date and time in string format
     */
    private DateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Creates a DateTime object based on whether there is a valid input
     * @param input the user's input
     * @return  a DateTime object
     * @throws WrongInputException
     */
    public static DateTime createDateTime(String input) throws WrongInputException {
        if (!DateTimeParser.isValidDateTime(input)) {
            throw new WrongInputException("Invalid date and time format",
                    "Please enter a valid date and time format");
        }
        return new DateTime(DateTimeParser.createLocalDateTime(input));
    }

    /**
     * Creates a DateTime object based on the input from storage
     * @param input the input from storage
     * @return  a DateTime object
     */
    public static DateTime createDateTimeFromStorage(String input) {
        try {
            return new DateTime(DateTimeParser.createLocalDateTime(input));
        } catch (WrongInputException e) {
            assert false : "Unable to create DateTime object from storage, file is likely corrupted";
            // Safe to return null here as the assert statement will terminate the program
            return null;
        }
    }

    /**
     * Gets the date and time in string format
     * @return  the date and time in string format
     */
    public String getDateTime() {
        for (String dateTimeOutput: DateTimeParser.VALID_DATE_TIME_FORMAT_OUTPUT) {
            try {
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(dateTimeOutput);
                String formattedTime = this.dateTime.format(outputFormatter);
                return formattedTime;
            } catch (Exception e) {
                System.out.println("Unable to output date and time for Date Time object" + e.toString());
            }
        }
        return this.dateTime.toString();
    }

    @Override
    public String toString() {
        return this.getDateTime();
    }

}
