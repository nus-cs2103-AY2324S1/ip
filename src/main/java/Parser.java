import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static void executeCommand(TaskList store, String[] commandTask) throws SaeException {
        String command = commandTask[0];

        try {
            if (command.equals("delete")) {
                int number = Integer.parseInt(commandTask[1]);
                store.deleteTask(number - 1);
            } else if (command.equals("list")) {
                store.listTasks();
            } else if (command.equals("mark")) {
                int number = Integer.parseInt(commandTask[1]);
                store.markTaskAsDone(number - 1);
            } else if (command.equals("unmark")) {
                int number = Integer.parseInt(commandTask[1]);
                store.unMarkTaskAsDone(number - 1);
            } else if (command.equals("todo")) {
                if (commandTask.length < 2 || commandTask[1].isEmpty()) {
                    throw new InvalidTodoException();
                }
                store.addToDoTask(commandTask[1]);
            } else if (command.equals("deadline")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/by")) {
                    throw new InvalidDeadlineException();
                }
                String[] parts = commandTask[1].split("/by");
                String description = parts[0].trim();
                String dateTimeString = parts[1].trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                store.addDeadlineTask(description, dateTime);
            } else if (command.equals("event")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
                    throw new InvalidEventException();
                }
                String[] parts = commandTask[1].split("/from|/to");
                store.addEventTask(parts[0].trim(), parts[1].trim(), parts[2].trim());
            } else {
                throw new SaeException();
            }
        } catch (SaeException errorMessage) {
            System.out.println(errorMessage.toString());
        }
    }
}
