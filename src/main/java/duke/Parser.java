package duke;

import duke.command.Command;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

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
