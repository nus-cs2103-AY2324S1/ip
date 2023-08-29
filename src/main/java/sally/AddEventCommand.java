package sally;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEventCommand implements Command {
    private String input;

    public AddEventCommand(String input) throws SallyException {
        this.input = input;
    }

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