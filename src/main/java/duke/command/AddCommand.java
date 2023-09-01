package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represent commands Todo, Deadline, Event which add tasks to the list.
 *
 * @author Armando Jovan Kusuma
 */
public class AddCommand extends Command{
    private String command;

    public AddCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @throws DukeException If the command execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (command.equalsIgnoreCase("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else if (command.equalsIgnoreCase("deadline")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (command.equalsIgnoreCase("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }

        String task = command.substring(0, command.indexOf(" "));
        if (task.startsWith("todo")) {
            Task currTask = new Todo(command.replace("todo", ""));
            tasks.add(currTask);
            ui.taskPrint(currTask, tasks.getTaskCount());
        } else if (task.startsWith("event")) {
            String[] s = command.replace("event ", "").split(" /from | /to ");
            try {
                Task currTask = new Event(s[0], s[1], s[2]);
                tasks.add(currTask);
                ui.taskPrint(currTask,tasks.getTaskCount());
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format! Please command date using the format yyyy-MM-dd");
            }
        } else if (task.startsWith("deadline")) {
            String[] s = command.replace("deadline ", "").split(" /by ");
            try {
                Task currTask = new Deadline(s[0], s[1]);
                tasks.add(currTask);
                ui.taskPrint(currTask,tasks.getTaskCount());
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date format! Please command date using the format yyyy-MM-dd");
            }
        } else {
            throw new DukeException("Oops! I'm sorry, I don't know what that means.");
        }
    }

}
