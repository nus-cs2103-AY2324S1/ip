package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.FixedTask;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents the Command Add that add a task to the list
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class AddCommand extends Command {
    private String description;

    /**
     * Constructs an AddCommand with a specified description of a task.
     *
     * @param description A string describing the task.
     */
    public AddCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddCommand and returns the message.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     * @throws DukeException If there are an invalid Input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = description.indexOf(" ");
        String taskDescription = description.substring(index + 1);
        if (index == -1) {
            if (taskDescription.equalsIgnoreCase("todo")) {
                throw new InvalidInputException("The description of a todo cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("deadline")) {
                throw new InvalidInputException("The description of a deadline cannot be empty.");
            } else if (taskDescription.equalsIgnoreCase("event")) {
                throw new InvalidInputException("The description of an event cannot be empty.");
            } else {
                throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
            }
        }
        String type = description.substring(0, index);
        if (type.equalsIgnoreCase("todo")) {
            tasks.add(new Todo(taskDescription));
        } else if (type.equalsIgnoreCase("deadline")) {
            int byIndex = taskDescription.indexOf("/by");
            if (byIndex == -1) {
                throw new InvalidInputException("Deadline must contain /by");
            }
            tasks.add(new Deadline(taskDescription));
        } else if (type.equalsIgnoreCase("event")) {
            int fromIndex = taskDescription.indexOf("/from");
            if (fromIndex == -1) {
                throw new InvalidInputException("Event must contain /from");
            }
            int toIndex = taskDescription.substring(fromIndex).indexOf("/to");
            if (toIndex == -1) {
                throw new InvalidInputException("Event must contain /to");
            }
            tasks.add(new Event(taskDescription));

        } else if (type.equalsIgnoreCase("fixed")) {
            int fromIndex = taskDescription.indexOf("/need");
            if (fromIndex == -1) {
                throw new InvalidInputException("Fixed Task must contain /need");
            }
            tasks.add(new FixedTask(taskDescription));
        } else {
            throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
        return ui.showAdd(tasks.getTask(tasks.getSize() - 1), tasks);
    }

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
