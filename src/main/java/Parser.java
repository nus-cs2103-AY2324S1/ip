import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static void parse (String userMessage, TaskList taskList) {
        String datePattern = "dd-MM-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        if (userMessage.equalsIgnoreCase("bye")) {
            Ui.endMessage();
        }
        if (userMessage.equalsIgnoreCase("list")) {
            Ui.listTasks(taskList);
            return;
        }
        try {
            if (userMessage.substring(0, 6).equalsIgnoreCase("delete")) {
                int removeTask = Integer.parseInt(userMessage.substring(7)) - 1;
                Ui.deleteMessage(removeTask, taskList);
            } else if (userMessage.substring(0, 4).equalsIgnoreCase("mark")) {
                int doneTask = Integer.parseInt(userMessage.substring(5)) - 1;
                Ui.markMessage(doneTask, taskList);
            } else if (userMessage.substring(0, 4).equalsIgnoreCase("todo")) {
                try {
                    Todo todo = new Todo(userMessage.substring(5));
                    Ui.todoMessage(todo, taskList);
                } catch (Exception e) {
                    Ui.formatErrorMessage("todo");
                }
            } else if (userMessage.substring(0, 8).equalsIgnoreCase("deadline")) {
                try {
                    int index = userMessage.indexOf("/by");
                    String description = userMessage.substring(9, index - 1);
                    LocalDateTime taskDeadline = LocalDateTime.parse(userMessage.substring(index + 4), formatter);
                    Deadline deadline = new Deadline(description, taskDeadline);
                    Ui.deadlineMessage(deadline, taskList);
                } catch (Exception e) {
                    Ui.formatErrorMessage("deadline");
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
                    Ui.eventMessage(event, taskList);
                } catch (Exception e) {
                    Ui.formatErrorMessage("event");
                }
            } else {
                Ui.inputErrorMessage();
            }
        } catch (Exception e) {
            Ui.inputErrorMessage();
        }
    }
}