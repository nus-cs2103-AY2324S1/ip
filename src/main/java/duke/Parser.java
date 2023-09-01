package duke;

import duke.command.Command;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

    /**
     * Parses a date and time in string and converts it to a LocalDateTime object.
     *
     * @param dateAndTime The date and time in string to be parsed.
     * @return A LocalDateTime object for the date and time in string format.
     * @throws DateTimeException If invalid date or time is passed in.
     */
    public LocalDateTime checkDateAndTime(String dateAndTime) {

        String[] dateSplit = dateAndTime.split("/");
        if (dateSplit.length < 4) {
            throw new DateTimeException("Please enter the time.");
        }
        int hr = Integer.parseInt(dateSplit[dateSplit.length - 1].substring(0, 2));
        int min = Integer.parseInt(dateSplit[dateSplit.length - 1].substring(2));
        return LocalDateTime.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[0]), hr, min);
    }

    /**
     * Parses a user input and returns Command instance.
     *
     * @param fullCommand The input of the user, including command and details.
     * @return A `Command` object with the command and details.
     */
    public static Command parse(String fullCommand) {

        //0: command 1: detail
        String[] splitted = fullCommand.split(" ", 2);
        Command c;

        if (splitted.length > 1) {
            return new Command(splitted[0], splitted[1]);
        } else {
            return new Command(splitted[0], "");
        }
    }
}
