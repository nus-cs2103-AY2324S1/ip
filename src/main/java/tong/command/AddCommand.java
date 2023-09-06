package tong.command;

import tong.task.*;

/**
 * Adds a new tong.task.Task to the tong.TaskList.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new task to the tasklist. "
            + "You may create either a todo, a deadline, or an event. Specify the type of the task using the keywords.\n"
            + "Please follow the format of the example given below.\n"
            + "Example: " + COMMAND_WORD
            + " todo watch Barbie\n"
            + "Example: " + COMMAND_WORD
            + " deadline cs2103 ip /by 2023-08-30 23:59\n"
            + "Example: " + COMMAND_WORD
            + " event pp's birthday party /from 2024-05-17 00:00 /to 2024-05-18 00:00\n";

    public static final String MESSAGE_ADD_TASK_SUCCESS = "New task added! Start working on it: %1$s";

    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute() {
        taskList.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_TASK_SUCCESS, toAdd));
    }
}
