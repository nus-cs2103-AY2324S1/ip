package duke.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of user commands
 */
public class Parser {

	/**
	 * Parses the user input to identify its important parts
	 *
	 * @param input
	 * @return
	 */
    public Input parse(String input) {
        String[] split = input.split(" ");
        String command = split[0].toLowerCase();
        int length = split.length;
        return new Input(command, input, length);
    }

    /**
     * Parses and formats the date input into another format
     *
     * @param strDate Date in String format
     * @return Date in "MMM dd yyyy" format
     */
    public String formatDate(String strDate) {
        String result = "Invalid date";
        try {
            LocalDate parseDate = LocalDate.parse(strDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            result = parseDate.format(formatter);
        } catch (DateTimeParseException e) {
            System.out.println("!ERROR! " + e + "\nDate needs to be in the form of yyyy-mm-dd");
        }
        return result;
    }
}
