package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represent commands Todo, Deadline, Event which add tasks to the list.
 *
 * @author Armando Jovan Kusuma
 */
public class AddCommand extends Command {
    private String command;


    public AddCommand(String command) {
        this.command = command;
    }

    public String todoCommand(String taskDescription, TaskList tasks, Ui ui) {
        if (taskDescription.isEmpty()) {
            return ui.errorPrint(new DukeException("OOPS! The description of a todo cannot be empty."));
        }
        Task todoTask = new Todo(taskDescription);
        tasks.add(todoTask);
        return ui.taskPrint(todoTask, tasks.getTaskCount());
    }

    public String deadlineCommand(String taskDescription, TaskList tasks, Ui ui) {
        if (taskDescription.isEmpty()) {
            return ui.errorPrint(new DukeException("OOPS! The description of a deadline cannot be empty."));
        }
        String[] deadlineParts = taskDescription.split(" /by ");
        if (deadlineParts.length != 2) {
            return ui.errorPrint(new DukeException("Oops! The deadline command is of the wrong format!"));
        }
        try {
            Task deadlineTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            tasks.add(deadlineTask);
            return ui.taskPrint(deadlineTask, tasks.getTaskCount());
        } catch (DateTimeParseException e) {
            return ui.errorPrint(
                    new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd"));
        }
    }

    public String eventCommand(String taskDescription, TaskList tasks, Ui ui) {
        if (taskDescription.isEmpty()) {
            return ui.errorPrint(new DukeException("OOPS! The description of an event cannot be empty."));
        }
        String[] eventParts = taskDescription.split(" /from | /to ");
        if (eventParts.length != 3) {
            return ui.errorPrint(new DukeException("Oops! The event command is of the wrong format!"));
        }
        try {
            Task eventTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
            tasks.add(eventTask);
            return ui.taskPrint(eventTask, tasks.getTaskCount());
        } catch (DateTimeParseException e) {
            return ui.errorPrint(
                    new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd"));
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @throws DukeException If the command execution fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String task = command.split(" ")[0].toLowerCase();
        String taskDescription = command.replaceFirst("(?i)" + task, "").trim();

        switch (task) {
        case "todo":
        case "t":
            return todoCommand(taskDescription, tasks, ui);
        case "deadline":
        case "d":
            return deadlineCommand(taskDescription, tasks, ui);
        case "event":
        case "e":
            return eventCommand(taskDescription, tasks, ui);
        default:
            return ui.errorPrint(new DukeException("Oops! I'm sorry, I don't know what that means..."));
        }
    }

}
