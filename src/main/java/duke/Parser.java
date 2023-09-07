package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Parser class that makes sense of user command and executes appropriate actions.
 */
public class Parser {

    /**
     * Parses the user command and executes appropriate methods.
     * @param command user input
     * @param ui Ui object that deals with user interaction
     * @param tasks TaskList object that stores all the tasks
     * @param storage Storage object that saves tasks into a file
     * @throws DukeException if command cannot be parsed
     * @throws IOException if file cannot be opened.
     */
    public static String parseAndExecute(String command, Ui ui, TaskList tasks, Storage storage)
            throws DukeException, IOException {
        if (command.equals("list")) {
            return ui.printList(tasks);
        } else {
            if (command.indexOf(" ") == -1) {
                throw new DukeException("Incorrect Format of Command");
            }
            String identifier = command.substring(0, command.indexOf(" "));
            String task = command.substring(command.indexOf(" ") + 1);
            String[] arr = task.split("[/]");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");

            switch (identifier) {
            case "todo":
                Task todo = new ToDo(task, false);
                tasks.addTask(todo);
                storage.appendToFile("T | " + todo.getStatusIcon() + " | "
                        + todo.taskDescription + System.lineSeparator());
                return ui.printAddedTask(tasks.getSize(), todo);
            case "deadline":
                String dl = arr[0];
                String by = arr[1].substring(3);
                LocalDateTime deadLineDateTime = LocalDateTime.parse(by, formatter);

                Task deadline = new Deadline(dl, deadLineDateTime, false);
                tasks.addTask(deadline);
                storage.appendToFile("D | " + deadline.getStatusIcon() + " | "
                        + deadline.taskDescription + "| " + by + System.lineSeparator());
                return ui.printAddedTask(tasks.getSize(), deadline);
            case "event":
                String ev = arr[0];
                String from = arr[1].substring(5, 5 + "yyyyMMdd HHmm".length());
                LocalDateTime eventStartDateTime = LocalDateTime.parse(from, formatter);
                String to = arr[2].substring(3);
                LocalTime eventEndDateTime = LocalTime.parse(to, formatter2);

                Task event = new Event(ev, eventStartDateTime, eventEndDateTime, false);
                tasks.addTask(event);
                storage.appendToFile("E | " + event.getStatusIcon() + " | "
                        + event.taskDescription + "| " + from + "-" + to + System.lineSeparator());
                return ui.printAddedTask(tasks.getSize(), event);
            case "mark":
                int indexToMark = java.lang.Integer.parseInt(task) - 1;
                tasks.changeStatusOfTask(indexToMark);
                storage.updateFileAfterMark(indexToMark + 1);
                return ui.printAfterMark(indexToMark, tasks);
            case "unmark":
                int indexToUnmark = java.lang.Integer.parseInt(task) - 1;
                tasks.changeStatusOfTask(indexToUnmark);
                storage.updateFileAfterUnmark(indexToUnmark + 1);
                return ui.printAfterUnmark(indexToUnmark, tasks);
            case "delete":
                int indexToDelete = java.lang.Integer.parseInt(task) - 1;
                Task removedTask = tasks.deleteTask(indexToDelete);
                storage.updateFileAfterDelete(indexToDelete + 1);
                return ui.printAfterDelete(tasks.getSize(), removedTask);
            case "find":
                String keyword = task;
                ArrayList<Task> tasksWithKeyword = tasks.findTaskUsingKeyword(keyword);
                return ui.printMatchingTasks(tasksWithKeyword);
            }
        }
        return "";
    }
}
