package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84
public class Parser {
    public static Command parseCommand(String userInput) throws DukeException {
        String[] split = userInput.split(" ");
        String command = split[0];
        String arguments = userInput.replaceFirst(command, "").trim();

        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "delete":
                int indexToDelete = Integer.parseInt(arguments) - 1;
                return new DeleteCommand(indexToDelete);
            case "todo":
                if (arguments.isEmpty()) {
                    throw new DukeException.ToDoException();
                }
                return new AddCommand(AddCommand.TaskType.TODO, arguments);
            case "event":
                if (arguments.isEmpty() || !arguments.contains("/from")) {
                    throw new DukeException.EventException();
                }

                String userCommandE = userInput.split(" ")[0];
                String argsE = userInput.replaceFirst(userCommandE, "").trim();
                String[] splitTheArgumentsE = argsE.split("/from", 2);

                if (splitTheArgumentsE.length != 2 || !splitTheArgumentsE[1].contains("/to")) {
                    throw new DukeException.EventFormatException();
                }
                String the_descriptionE = splitTheArgumentsE[0].trim();
                String[] theDateTimeE = splitTheArgumentsE[1].trim().split("/to", 2);

                String fromDateTime = theDateTimeE[0].trim();
                String toDateTime = theDateTimeE[1].trim();

                DateTimeValidator validator_e = new DateTimeValidator("yyyy/MM/dd HHmm");
                boolean isDateValid_e = validator_e.validateDate(fromDateTime) && validator_e.validateDate(toDateTime);

                if (isDateValid_e) {
                    LocalDateTime parsedFromDate = LocalDateTime.parse(fromDateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                    LocalDateTime parsedToDate = LocalDateTime.parse(toDateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                    return new AddCommand(AddCommand.TaskType.EVENT, the_descriptionE, parsedFromDate, parsedToDate);
                }
            case "deadline":
                // Solution below adapted and inspired by https://chat.openai.com/share/b706b4df-ab30-4d0f-93eb-b85617616319

                if (arguments.isEmpty()) {
                    throw new DukeException.DeadlineException();
                }

                String userCommandD = userInput.split(" ")[0];
                String argsD = userInput.replaceFirst(userCommandD, "").trim();
                String[] splitTheArgumentsD = argsD.split("/by", 2);

                if (splitTheArgumentsD.length != 2) {
                    throw new DukeException.DeadlineFormatException();
                }

                String the_descriptionD = splitTheArgumentsD[0];
                String[] theDateTimeD = splitTheArgumentsD[1].trim().split(" ", 2);

                String date = theDateTimeD[0];
                String time = theDateTimeD[1];

                DateTimeValidator validator = new DateTimeValidator("dd/MM/yyyy HHmm");
                boolean isDateValid = validator.validateDate(date + " " + time);

                if (isDateValid) {
                    LocalDateTime parsedDateTime = LocalDateTime.parse(date + " " + time,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    return new AddCommand(AddCommand.TaskType.DEADLINE, the_descriptionD, parsedDateTime);
                }
            case "unmark":
                if (arguments.isEmpty()) {
                    throw new DukeException.UnmarkException();
                }
                int indexToUnmark = Integer.parseInt(arguments) - 1;
                return new UnmarkCommand(indexToUnmark);
            case "mark":
                if (arguments.isEmpty()) {
                    throw new DukeException.MarkException();
                }
                int indexToMark = Integer.parseInt(arguments) - 1;
                return new MarkCommand(indexToMark);
            default:
                throw new DukeException.NoSuchItemException();
        }
    }
}