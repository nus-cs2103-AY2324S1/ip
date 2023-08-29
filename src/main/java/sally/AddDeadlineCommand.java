package sally;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineCommand implements Command {
    private String input;

    public AddDeadlineCommand(String input) throws SallyException {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        String[] parts = input.split("/by ");
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
            ui.showAddedTask(newDeadline, tasks.getSize());
        }
    }
    private static LocalDateTime convertToDateTime(String input) {
        String formattedDate;
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
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            return dateTime;
        } else if (matcher2.matches()) {
            LocalDateTime dateTime = LocalDateTime.parse(input + " 0000", inputFormatter);
            return dateTime;
        } else if (matcher3.matches()) {
            LocalDate today = LocalDate.now();
            LocalDateTime dateTime =
                    LocalDateTime.parse(today.toString() + " " + input, inputFormatter);
            return dateTime;
        } else {
            return null;
        }
    }
}
