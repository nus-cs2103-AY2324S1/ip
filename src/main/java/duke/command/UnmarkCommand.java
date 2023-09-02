package duke.command;

import duke.exception.DukeInvalidMarkException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Comment Class to unmark a task
 *
 * @author marioalvaro
 */
public class UnmarkCommand extends Command {
    private String[] splitTask;

    /**
     * A Constructor to make the UnmarkCommand.
     *
     * @param splitTask Array of String that contains the index for the mark
     */
    public UnmarkCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }

    /**
     * Method to execute the marking mechanism
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @throws Exception If there is an incorrect marking index
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkException(splitTask[0]);
        }

        if (index > 0 && taskList.get(index - 1) != null) {
            taskList.get(index - 1).unmark();
            ui.printUnmark(taskList.get(index - 1));
        }
        storage.writeFile(taskList);
    }
}
