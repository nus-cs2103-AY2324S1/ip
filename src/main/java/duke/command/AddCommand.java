package duke.command;

import java.io.IOException;
import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.Storage;
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
    public String execute(TaskList tasks, Response response, Storage storage) throws DukeException {
        try {
            Task newTask = createTask(); // Create a task based on the task type
            tasks.add(newTask);
            storage.appendToFile(newTask);
            return response.printTaskAdded(newTask, tasks.size());
        } catch (IOException e) {
            throw new DukeException("OOPS!!! There is something wrong with the description.");
        }
    }

    private Task createTask() throws DukeException {
        String description = commandDetails.get(0);
        assert this.taskType == "T" || this.taskType == "D" || this.taskType == "E" : "taskType should be T/E/D";
        switch (this.taskType) {
        case "T":
            return new Todo(description);
        case "D":
            String deadline = commandDetails.get(1);
            return new Deadline(description, deadline);
        case "E":
            String eventDate = commandDetails.get(1);
            String eventTime = commandDetails.get(2);
            return new Event(description, eventDate, eventTime);
        default:
            throw new DukeException("OOPS!!! Invalid task type.");
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
