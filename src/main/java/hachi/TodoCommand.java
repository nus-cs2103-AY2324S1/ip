package hachi;

import hachi.exceptions.EmptyTaskException;
import hachi.exceptions.HachiException;

/**
 * Represents the "todo" command.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private TaskList taskList;

    private Storage storage;

    /**
     * Constructor method for the TodoCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     * @param storage The storage used by the app.
     */
    public TodoCommand(String[] arguments, TaskList taskList, Storage storage) {
        super(arguments);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        Todo td = new Todo(String.join(" ", getArguments()));
        taskList.add(td);
        storage.updateTaskFile(taskList);
        return "Got it. I've added this task:\n   " + td
                + String.format("\nNow you have %d tasks in the list.", taskList.size());
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength < 1) {
            throw new EmptyTaskException(TodoCommand.COMMAND_WORD);
        }
    }
}
