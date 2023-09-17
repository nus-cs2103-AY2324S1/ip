package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    /**
     * Check if the input is a valid Date.
     * valid date format: dd/MM/yyyy HHmm
     *
     * @param input of String type
     * @return true if it is a valid date format
     */
    public static boolean isValidDate(String input) {
        String[] splitInput = input.split("/");

        if (splitInput.length != 3) {
            return false;
        }

        if (!isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");

        if (yearAndTime.length != 2) {
            return false;
        }

        if (!isNumeric(yearAndTime[0]) || !isNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    /**
     * Convert a valid String date to a different format.
     *
     * @param input of String type.
     * @return new String date format: MMM dd yyyy hh:mm a
     */
    public static String parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime d1 = LocalDateTime.parse(input, formatter);

            DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            return d1.format(returnFormatter);

        } catch (Exception e) {
            return input;
        }
    }

    /**
     * Check if the string input is a numeric characters.
     *
     * @param str of String type.
     * @return true if the str only contain numeric characters.
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); //match a number with optional '-' and decimal.
    }

}
