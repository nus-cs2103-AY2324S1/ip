package hachi;

import java.util.ArrayList;

import hachi.exceptions.HachiException;

/**
 * Represents the "find" command.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private TaskList taskList;

    /**
     * Constructor method for the FindCommand class.
     * @param arguments The arguments passed in by the user.
     * @param taskList The task list passed in by the user.
     */
    public FindCommand(String[] arguments, TaskList taskList) {
        super(arguments);
        this.taskList = taskList;
    }

    @Override
    public String execute() throws HachiException {
        checkArgumentLength(getArguments().length);
        String str = String.join(" ", getArguments());
        ArrayList<Task> filteredTasks = new ArrayList<>();
        taskList.iter(task -> {
            if (task.isStringWithinTaskName(str)) {
                filteredTasks.add(task);
            }
        });
        if (filteredTasks.isEmpty()) {
            return "No tasks found! Maybe try changing your search terms.";
        } else {
            return new TaskList(filteredTasks).toString();
        }
    }

    @Override
    protected void checkArgumentLength(int argumentLength) throws HachiException {
        if (argumentLength < 1) {
            throw new HachiException("Please include the word or words you are searching for!");
        }
    }
}
