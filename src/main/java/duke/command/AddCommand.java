package duke.command;

import java.time.format.DateTimeParseException;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.InvalidTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
public class AddCommand extends Command {
    private String description;
    public AddCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

        } else {
            throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
        ui.printAdd(tasks.getTask(tasks.length() - 1), tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
