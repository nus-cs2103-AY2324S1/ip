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
     * @return Message response from running the command.
     * @throws Exception If there is an incorrect marking index
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;
        String result;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkException(splitTask[0]);
        }

        if (index > 0 && index <= taskList.size() && taskList.get(index - 1) != null) {
            taskList.get(index - 1).unmark();
            result = ui.printUnmark(taskList.get(index - 1));
        } else {
            throw new DukeInvalidMarkException(Integer.toString(index));
        }
        storage.writeFile(taskList);

        return result;
    }
}
