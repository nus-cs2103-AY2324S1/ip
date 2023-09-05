package dot.commands;

import java.util.ArrayList;
import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.TaskList;
import dot.ui.Ui;

/**
 * FindCommand allows the user to find tasks based on a query.
 */
public class FindCommand extends Command {

    private final String query;

    private final TaskList dotTaskList;

    /**
     * Constructor for FindCommand.
     *
     * @param query       This is the query for the search.
     * @param dotTaskList This is the TaskList to search from.
     */
    public FindCommand(String query, TaskList dotTaskList) {
        this.query = query;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Finds and displays queried tasks.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error.
     */
    @Override
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        ArrayList<String> queriedTasks = dotTaskList.getDisplayForQueriedTasks(this.query);
        handleDotOutput.accept(Ui.concatArrayList(queriedTasks));
    }
}
