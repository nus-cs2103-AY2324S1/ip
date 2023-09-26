import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for reading user input.
 */
public class Parser {
    /**
     * Parses any user input.
     *
     * @param userMessage Input of the user.
     * @param taskList TaskList with all the current tasks.
     */
    public static String parse (String userMessage, TaskList taskList) {
        assert userMessage.length() > 0;
        String datePattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        if (userMessage.equalsIgnoreCase("bye")) {
            return Ui.endMessage();
        }
        if (userMessage.equalsIgnoreCase("list")) {
            return Ui.listTasks(taskList);
        }
        try {
            if (userMessage.substring(0, 6).equalsIgnoreCase("delete")) {
                int removeTask = Integer.parseInt(userMessage.substring(7)) - 1;
                return Ui.deleteMessage(removeTask, taskList);
            } else if (userMessage.substring(0, 4).equalsIgnoreCase("find")) {
                return Ui.findMessage(userMessage.substring(5), taskList);
            }else if (userMessage.substring(0, 4).equalsIgnoreCase("mark")) {
                int doneTask = Integer.parseInt(userMessage.substring(5)) - 1;
                return Ui.markMessage(doneTask, taskList);
            } else if (userMessage.substring(0, 4).equalsIgnoreCase("todo")) {
                try {
                    Todo todo = new Todo(userMessage.substring(5));
                    return Ui.todoMessage(todo, taskList);
                } catch (Exception e) {
                    return Ui.formatErrorMessage("todo");
                }
            } else if (userMessage.substring(0, 8).equalsIgnoreCase("deadline")) {
                try {
                    int index = userMessage.indexOf("/by");
                    String description = userMessage.substring(9, index - 1);
                    LocalDateTime taskDeadline = LocalDateTime.parse(userMessage.substring(index + 4), formatter);
                    Deadline deadline = new Deadline(description, taskDeadline);
                    return Ui.deadlineMessage(deadline, taskList);
                } catch (Exception e) {
                    return Ui.formatErrorMessage("deadline");
                }
            } else if (userMessage.substring(0, 5).equalsIgnoreCase("event")) {
                try {
                    int index = userMessage.indexOf("/from");
                    int index2 = userMessage.indexOf("/to");
                    String description = userMessage.substring(6, index - 1);
                    String start = userMessage.substring(index + 6, index2 - 1);
                    String end = userMessage.substring(index2 + 4);
                    LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                    LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                    Event event = new Event(description, startTime, endTime);
                    return Ui.eventMessage(event, taskList);
                } catch (Exception e) {
                    return Ui.formatErrorMessage("event");
                }
            } else {
                return Ui.inputErrorMessage();
            }
        } catch (Exception e) {
            return Ui.inputErrorMessage();
        }
    }
}