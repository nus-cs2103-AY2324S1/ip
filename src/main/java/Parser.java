import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static Command parse(String fullCommand) {
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0];
        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(splitInput[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(splitInput[1]));
            case "todo":
//                if (splitInput.length == 1) {
//                    ui.showErrorMessage("The description of a todo cannot be empty\n");
//                    break;
//                }
                return new AddCommand(new Todo(splitInput[1]));
            case "deadline":
                String[] splitInputBy = splitInput[1].split(" /by ", 2);
                return new AddCommand(
                        new Deadline(splitInputBy[0], LocalDateTime.parse(splitInputBy[1], dateTimeFormat)));
            case "event":
                String[] splitInputFrom = splitInput[1].split(" /from ", 2);
                String[] splitInputTo = splitInputFrom[1].split(" /to ", 2);
                return new AddCommand(
                        new Event(splitInputFrom[0], LocalDateTime.parse(splitInputTo[0], dateTimeFormat),
                                LocalDateTime.parse(splitInputTo[1], dateTimeFormat)));
            case "delete":
                return new DeleteCommand(Integer.parseInt(splitInput[1]));
            default:
                return new ExitCommand(); // TODO add exception
//                ui.showErrorMessage("I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
