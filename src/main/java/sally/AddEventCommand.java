package sally;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand implements Command {
    private final String input;

    /**
     * Constructs an AddEventCommand object with the given input.
     *
     * @param input The input containing task details.
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the AddEventCommand to add an event task.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @param ui The Ui for user interaction.
     * @throws SallyException If there's an issue while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        String[] parts = input.split("/from | /to ");
        if (parts.length < 3) {
            throw new SallyException(
                    "OOPS! The description of an event cannot be incomplete, you need a '/from' and '/to'.");
        }

        String taskDescription = parts[0];
        String fromDateTimeInput = parts[1];
        String toDateTimeInput = parts[2];

        if (convertToDateTime(fromDateTimeInput) == null || convertToDateTime(toDateTimeInput) == null) {
            throw new SallyException("OOPS! The date and time format is invalid.");
        } else {
            Event newEvent =
                    new Event(taskDescription,
                            convertToDateTime(fromDateTimeInput),
                            convertToDateTime(toDateTimeInput));
            tasks.addTask(newEvent);
            storage.saveTasksToFile(tasks);
            ui.showAddedTask(newEvent, tasks.getSize());
        }
    }

    /**
     * Converts the input string to a LocalDateTime object.
     *
     * @param input The input string containing date and time.
     * @return The converted LocalDateTime object, or null if format is invalid.
     */
    private static LocalDateTime convertToDateTime(String input) {
        String pattern1 = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}";
        String pattern2 = "\\d{4}-\\d{1,2}-\\d{1,2}";
        String pattern3 = "\\d{4}";
        Pattern regexPattern1 = Pattern.compile(pattern1);
        Pattern regexPattern2 = Pattern.compile(pattern2);
        Pattern regexPattern3 = Pattern.compile(pattern3);
        Matcher matcher1 = regexPattern1.matcher(input);
        Matcher matcher2 = regexPattern2.matcher(input);
        Matcher matcher3 = regexPattern3.matcher(input);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");

        if (matcher1.matches()) {
            return LocalDateTime.parse(input, inputFormatter);
        } else if (matcher2.matches()) {
            return LocalDateTime.parse(input + " 0000", inputFormatter);
        } else if (matcher3.matches()) {
            LocalDate today = LocalDate.now();
            return LocalDateTime.parse(today + " " + input, inputFormatter);
        } else {
            return null;
        }
    }
}
