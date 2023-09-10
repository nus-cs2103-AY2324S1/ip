package tasket.parser;

import tasket.exception.TasketException;
import tasket.command.*;
import tasket.task.Deadline;
import tasket.task.Event;
import tasket.task.Task;
import tasket.task.ToDo;

public class Parser {

    public static Command parseInput(String command) throws TasketException {
        String[] commandParts = command.split(" ", 2);
        switch (commandParts[0]) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandParts[0], command);
        case "delete":
            return new DeleteCommand(command.replaceFirst("delete", "").trim());
        case "mark":
            return new MarkCommand(command.replaceFirst("mark", "").trim());
        case "unmark":
            return new UnmarkCommand(command.replaceFirst("unmark", "").trim());
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new TasketException("I'm sorry, but I don't know what it means :(");
        }
    }

    public static Task parseSaveString(String taskSave) throws TasketException {
        String[] taskElements = taskSave.split(" \\| ");
        Task task;
        switch (taskElements[0]) {
        case "T":
            if (taskElements.length < 3) {
                throw new TasketException("Error while retrieving task. The task will not be loaded");
            }
            task = new ToDo(taskElements[2]);
            break;
        case "D":
            if (taskElements.length < 4) {
                throw new TasketException("Error while retrieving task. The task will not be loaded");
            }
            task = new Deadline(taskElements[2], taskElements[3]);
            break;
        case "E":
            if (taskElements.length < 5) {
                throw new TasketException("Error while retrieving task. The task will not be loaded");
            }
            task = new Event(taskElements[2], taskElements[3], taskElements[4]);
            break;
        default:
            throw new TasketException("Error while retrieving task. The task will not be loaded");
        }

        if (taskElements[1].equals("1")) {
            task.markAsDone();
        }

        return task;

    }
}
