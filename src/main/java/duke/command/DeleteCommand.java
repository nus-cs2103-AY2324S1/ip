package duke.command;

import duke.exception.DukeInvalidDeleteException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class to execute delete task.
 *
 * @author marioalvaro
 */
public class DeleteCommand extends Command{
    private String[] splitTask;

    /**
     * Constructor that create DeleteCommand.
     *
     * @param splitTask Array of String that contains the index for the mark
     */
    public DeleteCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }

    /**
     * Method to execute delete mechanism
     *
     * @param taskList The used TaskList
     * @param ui The ui object
     * @param storage The storage used
     * @throws Exception Throw Exception if index is incorrect
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidDeleteException(splitTask[0]);
        }

        if (index > 0 && taskList.get(index - 1) != null) {
            ui.printDeleteTask(taskList.get(index-1), taskList.size() - 1);
            taskList.remove(index - 1);

        }
        storage.writeFile(taskList);
    }
}
