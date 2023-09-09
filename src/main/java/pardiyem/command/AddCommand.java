package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.Deadline;
import pardiyem.task.Event;
import pardiyem.task.Task;
import pardiyem.task.TaskList;
import pardiyem.task.Todo;
import pardiyem.ui.Ui;

/**
 * Represents a command that involves adding Task objects to the tasklist
 */
public class AddCommand extends Command {
    private Task toAdd;

    /**
     * A constructor for the AddCommand class
     *
     * @param desc additional arguments to construct a Task class
     * @param type indicates which child of the Task class will it add: 1 for Todo, 2 for Deadline, 3 for Event
     */
    public AddCommand(String desc, int type) {
        super(desc);
        switch (type) {
        case 1:
            toAdd = new Todo(desc);
            break;
        case 2: {
            toAdd = new Deadline(desc);
            break;
        }
        case 3: {
            toAdd = new Event(desc);
            break;
        }
        default:
            break;
        }
    }

    /**
     * Utility inherited method to indicate whether this command will end the program
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Inherited method to execute the command.
     * Will add the constructed Task object to the tasklist,
     * send a message to the UI, and store the current state of the tasklist
     *
     * @param taskList the TaskList object to modify
     * @param ui the Ui object to send the message to
     * @param storage the Storage object that will handle the saving to the data file
     * @throws IOException if the Storage object fails to write to the data file
     */
    public String execute(TaskList taskList, Storage storage) throws IOException {
        taskList.add(toAdd);
        storage.save(taskList);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list",
                toAdd.toString(), taskList.size());
    };

    /**
     * Utility inherited method to compare two objects.
     * Comparison between two AddCommand objects will be done through comparison of their toAdd attribute
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof AddCommand) {
            result = this.toAdd.equals(((AddCommand) obj).toAdd);
        }
        return result;
    }
}
