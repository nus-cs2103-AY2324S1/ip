package dot.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

import dot.errors.DotException;
import dot.tasks.TaskList;
import dot.ui.Ui;

/**
 * Command to list out all tasks happening on a given date.
 */
public class WhatsgoingonCommand extends Command {

    private final LocalDateTime parsedLocalDateTime;

    private final TaskList dotTaskList;

    /**
     * Constructor for WhatsgoingonCommand.
     *
     * @param parsedLocalDateTime This is the LocalDateTime object representing queried Date.
     * @param dotTaskList         This is the TaskList which encapsulates the Task and operations.
     */
    public WhatsgoingonCommand(LocalDateTime parsedLocalDateTime,
                               TaskList dotTaskList) {
        this.parsedLocalDateTime = parsedLocalDateTime;
        this.dotTaskList = dotTaskList;
    }

    /**
     * Queries and displays all tasks falling on parsedLocalDateTime.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     * @throws DotException On detected error
     */
    public void execute(Consumer<String> handleDotOutput) throws DotException {
        ArrayList<String> queriedTasks = dotTaskList.getDisplayForTasksFallingOnDate(parsedLocalDateTime);
        handleDotOutput.accept(Ui.concatArrayList(queriedTasks));
    }

}
