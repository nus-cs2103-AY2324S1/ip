package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * The AddCommand class represents a command to add a task.
 * It is a subclass of the Command class.
 */
public class AddCommand extends Command {
    private String taskType;

    /**
     * Constructs an AddCommand object with the provided command details and task type.
     *
     * @param commandDetails The list of command details, which may include task information.
     * @param taskType       The type of task to add (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public AddCommand(ArrayList<String> commandDetails, String taskType) {
        super(commandDetails);
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.taskType) {
        case "T":
            Task newTodo = new Todo(commandDetails.get(0));
            tasks.add(newTodo);
            try {
                storage.appendToFile(newTodo);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newTodo, tasks.size());
            break;
        case "D":
            Task newDeadline = new Deadline(commandDetails.get(0), commandDetails.get(1));
            tasks.add(newDeadline);
            try {
                storage.appendToFile(newDeadline);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newDeadline, tasks.size());
            break;
        case "E":
            Task newEvent = new Event(commandDetails.get(0), commandDetails.get(1), commandDetails.get(2));
            tasks.add(newEvent);
            try {
                storage.appendToFile(newEvent);
            } catch (IOException e) {
                throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
            }
            ui.printTaskAdded(newEvent, tasks.size());
            break;
        default:
            throw new DukeException("☹ OOPS!!! Something went wrong when adding the task.");
        }
    }

    /**
     * Checks if this AddCommand is equal to another object. Two AddCommands are considered equal
     * if they have the same command details and task type.
     *
     * @param obj The object to compare to this AddCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails) && this.taskType.equals(other.taskType);
        }
        return false;
    }
}
