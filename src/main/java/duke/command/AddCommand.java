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

    /**
     * Executes the command.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The UI which functions as the user interface of the Chat bot.
     * @param storage The storage file to store the list of tasks.
     * @throws DukeException If the command execution fails.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (command.equalsIgnoreCase("todo")) {
            return ui.errorPrint(new DukeException("OOPS! The description of a todo cannot be empty."));
        } else if (command.equalsIgnoreCase("deadline")) {
            return ui.errorPrint(new DukeException("OOPS! The description of a deadline cannot be empty."));
        } else if (command.equalsIgnoreCase("event")) {
            return ui.errorPrint(new DukeException("OOPS! The description of an event cannot be empty."));
        }
        String task = command.substring(0, command.indexOf(" "));
        if (task.toLowerCase().startsWith("todo")) {
            Task currTask = new Todo(command.replaceFirst("(?i)todo", ""));
            tasks.add(currTask);
            return ui.taskPrint(currTask, tasks.getTaskCount());
        } else if (task.toLowerCase().startsWith("event")) {
            String[] s = command.replaceFirst("(?i)event ", "").split(" /from | /to ");
            try {
                Task currTask = new Event(s[0], s[1], s[2]);
                tasks.add(currTask);
                return ui.taskPrint(currTask, tasks.getTaskCount());
            } catch (DateTimeParseException e) {
                return ui.errorPrint(
                        new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd")
                );
            } catch (Exception e) {
                return ui.errorPrint(new DukeException("Oops! The event command is of the wrong format!"));
            }
        } else if (task.toLowerCase().startsWith("deadline")) {
            String[] s = command.replaceFirst("(?i)deadline ", "").split(" /by ");
            try {
                Task currTask = new Deadline(s[0], s[1]);
                tasks.add(currTask);
                return ui.taskPrint(currTask, tasks.getTaskCount());
            } catch (DateTimeParseException e) {
                return ui.errorPrint(
                        new DukeException("Invalid date format! Please input date using the format yyyy-MM-dd")
                );
            } catch (Exception e) {
                return ui.errorPrint(new DukeException("Oops! The deadline command is of the wrong format!"));
            }
        } else {
            return ui.errorPrint(new DukeException("Oops! I'm sorry, I don't know what that means."));
        }
    }

}
