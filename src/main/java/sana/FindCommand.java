package sana;

import java.util.ArrayList;

/**
 * Represents a command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand object.
     *
     * @param cmd type of command (e.g. find, delete, etc).
     * @param arguments arguments associated with the command.
     */
    public FindCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes find command task by finding matching tasks in the list/file and printing them out.
     *
     * @param tasks The task list on which the keyword is to be matched against.
     * @param ui The user interface handling the command execution.
     * @param storage The storage manager for persisting task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(getArguments())) {
                matchingTasks.add(currTask);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int j = 0; j < matchingTasks.size(); j++) {
            System.out.println(j + 1 + ". " + matchingTasks.get(j).toString());
        }
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return returns false as add command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
