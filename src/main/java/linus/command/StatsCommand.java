package linus.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command that finds tasks in the task list.
 */
public class StatsCommand extends Command {
    private static final String STATS_COMMAND_REGEX =
            "/duration (\\d+)"
                    + "( /task (todo|deadline|event))?"
                    + "( /done)?";
    private static final String STATS_COMMAND_INCORRECT_FORMAT_MESSAGE =
            "☹ OOPS!!! Please specify the stats in the correct "
                    + "format: \n"
                    + "stats /duration <number of days> /task <taskType> /done \n"
                    + "where /task <taskType> and /done are optional";
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructs a command that finds tasks in the task list.
     * @param tasks
     * @param data
     * @param ui
     */
    public StatsCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Finds the tasks in the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        Pattern pattern = Pattern.compile(STATS_COMMAND_REGEX);
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            int duration = Integer.parseInt(matcher.group(1));
            String taskType = null;
            if (matcher.group(2) != null) {
                taskType = matcher.group(3);
            }
            boolean isFilterByDone = matcher.group(4) != null;
            ui.printStats(tasks.showStats(duration, taskType, isFilterByDone));
        } else {
            throw new LinusException(
                    STATS_COMMAND_INCORRECT_FORMAT_MESSAGE
            );
        }

    }

}
