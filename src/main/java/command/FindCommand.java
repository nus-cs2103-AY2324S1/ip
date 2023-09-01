package command;

import duke.*;

import java.util.ArrayList;

public class FindCommand extends Command{
    /**
     * Command to fina tasks by keywords.
     */
    public static final String COMMAND_FIND = "find";

    /**
     * Constructor for the FindCommand class.
     *
     * @param params Parsed user input.
     */
    public FindCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 2) {
            throw new InvalidCommandException("Add todo command format is wrong");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> tasksContainingKeyword = tasks.getTasksContainingKeyword(params.get(1));
        ui.printTasksMatching(tasksContainingKeyword);
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
