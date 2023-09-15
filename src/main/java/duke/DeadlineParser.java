package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;

/**
 * The DeadlineParser class is responsible for parsing user input and creating an AddCommand
 * for deadline-related tasks in the Duke application.
 */
public class DeadlineParser {

    /**
     * Parses the user input and creates an AddCommand for a deadline task.
     *
     * @param input The user input containing deadline task details.
     * @return An AddCommand object representing the deadline task.
     * @throws DukeException.DeadlineFormatException If the input format for the deadline task is incorrect.
     * @throws DukeException.DeadlineException       If there is an issue creating the deadline task.
     */
    public static AddCommand parseDeadlineCommand(String input) throws DukeException {
        // Split the input and perform parsing here
        String userCommandD = input.split(" ")[0];
        String argsD = input.replaceFirst(userCommandD, "").trim();
        String[] splitTheArgumentsD = argsD.split("/by", 2);

        if (splitTheArgumentsD.length != 2) {
            throw new DukeException.DeadlineFormatException();
        }

        String theDescriptionD = splitTheArgumentsD[0];
        String[] theDateTimeD = splitTheArgumentsD[1].trim().split(" ", 2);

        String date = theDateTimeD[0];
        String time = theDateTimeD[1];

        DateTimeValidator validator = new DateTimeValidator("dd/MM/yyyy HHmm");
        boolean isDateValid = validator.validateDate(date + " " + time);

        if (isDateValid) {
            LocalDateTime parsedDateTime = LocalDateTime.parse(date + " " + time,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return new AddCommand(AddCommand.TaskType.DEADLINE, theDescriptionD, parsedDateTime);
        } else {
            throw new DukeException.DeadlineException();
        }
    }
}
