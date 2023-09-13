package command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * A command to generate statistics on the tasks.
 */
public class StatisticsCommand extends Command {
    /**
     * Command to get statistics on tasks.
     */
    public static final String COMMAND_STATISTICS = "stats";

    /**
     * Constructor for the StatisticsCommand class.
     *
     * @param params Parsed user input.
     */
    public StatisticsCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 1) {
            throw new InvalidCommandException("Statistics command format is wrong");
        }
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        return ui.getStatisticsMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
