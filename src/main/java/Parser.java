// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

                String userCommand_e = userInput.split(" ")[0];
                String args_e = userInput.replaceFirst(userCommand_e, "").trim();
                String[] split_the_arguments_e = args_e.split("/from", 2);

                if (split_the_arguments_e.length != 2 || !split_the_arguments_e[1].contains("/to")) {
                    throw new DukeException.EventFormatException();
                }
                String the_description_e = split_the_arguments_e[0].trim();
                String[] theDateTime_e = split_the_arguments_e[1].trim().split("/to", 2);

                String fromDateTime = theDateTime_e[0].trim();
                String toDateTime = theDateTime_e[1].trim();

                DateTimeValidator validator_e = new DateTimeValidator("yyyy/MM/dd HHmm");
                boolean isDateValid_e = validator_e.validateDate(fromDateTime) && validator_e.validateDate(toDateTime);

                if (isDateValid_e) {
                    LocalDateTime parsedFromDate = LocalDateTime.parse(fromDateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                    LocalDateTime parsedToDate = LocalDateTime.parse(toDateTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                    return new AddCommand(AddCommand.TaskType.EVENT, the_description_e, parsedFromDate, parsedToDate);
                }
            case "deadline":
                // Solution below adapted and inspired by https://chat.openai.com/share/b706b4df-ab30-4d0f-93eb-b85617616319

                if (arguments.isEmpty()) {
                    throw new DukeException.DeadlineException();
                }

                String userCommand_d = userInput.split(" ")[0];
                String args_d = userInput.replaceFirst(userCommand_d, "").trim();
                String[] split_the_arguments_d = args_d.split("/by", 2);

                if (split_the_arguments_d.length != 2) {
                    throw new DukeException.DeadlineFormatException();
                }

                String the_description_d = split_the_arguments_d[0];
                String[] theDateTime_d = split_the_arguments_d[1].trim().split(" ", 2);

                String date = theDateTime_d[0];
                String time = theDateTime_d[1];

                DateTimeValidator validator = new DateTimeValidator("dd/MM/yyyy HHmm");
                boolean isDateValid = validator.validateDate(date + " " + time);

                if (isDateValid) {
                    LocalDateTime parsedDateTime = LocalDateTime.parse(date + " " + time,
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                    return new AddCommand(AddCommand.TaskType.DEADLINE, the_description_d, parsedDateTime);
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