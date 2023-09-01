package rocket;

import java.time.LocalDateTime;

public class Parser {

    /**
     * Parses the line command that the user types into a Command
     * @param fullCommand the String of user input.
     * @return a Command that will be executed.
     * @throws RocketException if user enters an invalid command.
     */
    public static Command parse(String fullCommand) throws RocketException{
        String command;
        String arguments;
        int firstWordIndex = fullCommand.indexOf(' ');
        if (firstWordIndex == -1) {
            command = fullCommand;
            arguments = "";
        } else {
            command = fullCommand.substring(0, firstWordIndex);
            arguments = fullCommand.substring(firstWordIndex + 1);
        }
        switch (command) {
        case "bye": {
            return new ExitCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "find": {
            return new FindCommand(arguments);
        }
        case "mark": {
            return new MarkCommand(Integer.parseInt(arguments) - 1);
        }
        case "unmark": {
            return new UnmarkCommand(Integer.parseInt(arguments) - 1);
        }
        case "delete": {
            return new DeleteCommand(Integer.parseInt(arguments) - 1);
        }
        case "todo": {
            return new TodoCommand(arguments, false);
        }
        case "deadline": {
            int descriptionIndex = arguments.indexOf("by") - 2;
            String description = arguments.substring(0, descriptionIndex);
            String deadline = arguments.substring(descriptionIndex + 5)
                    .replace(')', ' ')
                    .trim();

            if (description.isEmpty()) {
                throw new RocketIllegalArgumentException("The description of a deadline");
            }
            if (deadline.isEmpty()) {
                throw new RocketIllegalArgumentException("The deadline of a deadline");
            }
            LocalDateTime by = LocalDateTime.parse(deadline, Rocket.uglyDateTimeFormatter);
            return new DeadlineCommand(description, false, by);
        }
        case "event": {
            int descriptionIndex = arguments.indexOf("from") - 2;
            String description = arguments.substring(0, descriptionIndex);
            if (description.isEmpty()) {
                throw new RocketIllegalArgumentException("The description of an event.");
            }
            String duration = arguments.substring(descriptionIndex + 7)
                    .trim();
            if (duration.isBlank()) {
                throw new RocketIllegalArgumentException("The duration of an event");
            }
            int fromIndex = duration.indexOf("to") - 1;
            LocalDateTime from = LocalDateTime.parse(
                    duration.substring(0, fromIndex).trim(),
                    Rocket.uglyDateTimeFormatter
            );
            LocalDateTime to = LocalDateTime.parse(
                    duration.substring(fromIndex + 4)
                            .replace(')', ' ')
                            .trim(),
                    Rocket.uglyDateTimeFormatter
            );
            return new EventCommand(description, false, from, to);
        }
        default: {
            throw new RocketInvalidCommandException();
        }
        }
    }
}
