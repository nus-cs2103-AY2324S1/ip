package kevin.evaluator;

import kevin.taskList.TaskList;
import java.util.ArrayList;

/**
 * A class responsible for the logic for BYE command.
 */
public class ByeStrategy extends BaseStrategy {
    /**
     * Constructor to initialize ByeStrategy.
     * @param taskList This is the TaskList where the tasks are stored and operations are defined.
     * @param arguments This is an ArrayList where all the needed arguments are stored.
     */
    public ByeStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }
}
