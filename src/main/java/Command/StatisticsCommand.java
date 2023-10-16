package command;

import duke.DukeException;
import storage.Storage;
import task.Statistics;
import tasklist.TaskList;
import ui.Ui;

/**
 * The `StatisticsCommand` class represents a command to calculate and display task statistics.
 * It calculates statistics such as the number of tasks completed within the last week,
 * the total number of tasks completed, and the percentages of tasks completed.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class StatisticsCommand extends Command {
    /**
     * Executes the statistics command by calculating task statistics and returning the result as a formatted string.
     *
     * @param taskList The `TaskList` containing the tasks for which statistics will be calculated.
     * @param ui       The user interface component used to display the statistics.
     * @param storage  The storage component used to load and save data (not used in this command).
     * @return A formatted string containing the calculated task statistics.
     * @throws DukeException If there is an error calculating or displaying the statistics.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Statistics statistics = taskList.calculateStatistics();
        return ui.showStatistics(statistics);
    }
}
