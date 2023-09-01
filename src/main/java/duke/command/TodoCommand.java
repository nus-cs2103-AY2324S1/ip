package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.UI;

/**
 * Command to create a Todo Task.
 */
public class TodoCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "todo";
    private final String arguments;

    /**
     * Constructor for TodoCommand
     *
     * @param arguments arguments for TodoCommand
     */
    public TodoCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Validate arguments to this command.
     * They must not be empty
     *
     * @param arguments arguments to validate
     * @throws DukeException if arguments are invalid
     */
    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
    }

    /**
     * Create a Todo Task
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if unable to create Todo task
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        taskList.add(new Todo(this.arguments));
        if (ui != null) {
            ui.sendMessage("Got it. I've added this task:\n  "
                    + taskList.get(taskList.size() - 1)
                    + String.format("\nNow you have %d tasks in the list.", taskList.size()));
        }
        storage.updateFile(taskList, ui);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
