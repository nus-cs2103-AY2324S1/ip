package sally;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand implements Command {
    private final String input;
    private Message message;

    /**
     * Constructs an AddDeadlineCommand with the given input.
     *
     * @param input The input string containing task details.
     */

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a new Deadline task to the task list.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @throws SallyException If there's an issue while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        String[] parts = input.split("/by ");
        message = new Message();

        if (parts.length < 2) {
            throw new SallyException(
                    "OOPS! The description of a deadline cannot be incomplete, you need a ' /by '.");
        }

        String taskDescription = parts[0];
        String dateTimeInput = parts[1];

        if (convertToDateTime(dateTimeInput) == null) {
            throw new SallyException("OOPS! The date and time format is invalid.");
        } else {
            Deadline newDeadline = new Deadline(taskDescription, convertToDateTime(dateTimeInput));
            tasks.addTask(newDeadline);
            storage.saveTasksToFile(tasks);
            return message.addMessage(newDeadline, tasks.getTaskList().size());
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
