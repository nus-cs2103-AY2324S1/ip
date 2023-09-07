package duke.command;

import duke.TaskType;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.time.LocalDate;

public class Parser {
    public Parser() {
    }

    public static boolean isBye(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public static boolean isList(String command) {
        return command.equalsIgnoreCase("list");
    }

    public static boolean isMarkDone(String command) {
        return command.startsWith("mark");
    }

    public static boolean isMarkNotDone(String command) {
        return command.startsWith("unmark");
    }

    public static boolean isDelete(String command) {
        return command.startsWith("delete");
    }

    public static int extractTaskIndex(String command) throws DukeException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        } else {
            return Integer.parseInt(parts[1].trim()) - 1;
        }
    }

    public static Task parseTask(String command) throws DukeException {
        String[] parts = command.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());
            switch (taskType) {
                case TODO:
                    String[] todoParts = parts[1].split(" /from ");
                    if (todoParts.length != 2) {
                        throw new DukeException("OOPS!!! Todo tasks must include '/from' for date.");
                    } else {
                        String[] dateRange = todoParts[1].split(" /to ");
                        if (dateRange.length != 2) {
                            throw new DukeException("OOPS!!! Todo tasks must include '/to' for the end date.");
                        }

                        LocalDate fromDate = LocalDate.parse(dateRange[0]);
                        LocalDate toDate = LocalDate.parse(dateRange[1]);
                        return new TodoTask(todoParts[0], fromDate, toDate, false);
                    }
                case DEADLINE:
                    String[] deadlineParts = parts[1].split(" /by ");
                    if (deadlineParts.length != 2) {
                        throw new DukeException("OOPS!!! Deadline tasks must include '/by' for date.");
                    }

                    LocalDate byDate = LocalDate.parse(deadlineParts[1]);
                    return new DeadlineTask(deadlineParts[0], byDate, false);
                case EVENT:
                    String[] eventParts = parts[1].split(" /at ");
                    if (eventParts.length != 2) {
                        throw new DukeException("OOPS!!! Event tasks must include '/at' for date.");
                    }

                    LocalDate atDate = LocalDate.parse(eventParts[1]);
                    return new EventTask(eventParts[0], atDate, false);
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException var11) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseFileLine(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length != 3) {
            throw new DukeException("Invalid task format in the file.");
        } else {
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDate byDate;
            switch (taskType) {
                case "T":
                    String[] todoParts = description.split(" \\(from: | to: ", 3);
                    if (todoParts.length == 3) {
                        LocalDate fromDate = LocalDate.parse(todoParts[1]);
                        byDate = LocalDate.parse(todoParts[2].substring(0, todoParts[2].length() - 1));
                        return new TodoTask(todoParts[0], fromDate, byDate, isDone);
                    }

                    throw new DukeException("Invalid todo task format in the file.");
                case "D":
                    String[] deadlineParts = description.split(" \\(by: ", 2);
                    if (deadlineParts.length == 2) {
                        byDate = LocalDate.parse(deadlineParts[1].substring(0, deadlineParts[1].length() - 1));
                        return new DeadlineTask(deadlineParts[0], byDate, isDone);
                    }

                    throw new DukeException("Invalid deadline task format in the file.");
                case "E":
                    String[] eventParts = description.split(" \\(at: ", 2);
                    if (eventParts.length == 2) {
                        LocalDate atDate = LocalDate.parse(eventParts[1].substring(0, eventParts[1].length() - 1));
                        return new EventTask(eventParts[0], atDate, isDone);
                    }

                    throw new DukeException("Invalid event task format in the file.");
                default:
                    throw new DukeException("Unknown task type in file.");
            }
        }
    }
}
