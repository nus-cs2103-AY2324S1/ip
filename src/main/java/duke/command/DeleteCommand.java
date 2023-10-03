package duke.command;

import duke.Duplicate.Duplicate;
import duke.exception.DukeInvalidDeleteException;
import duke.exception.DukeInvalidMarkException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Command Class to execute delete task.
 *
 * @author marioalvaro
 */
public class DeleteCommand extends Command {
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
     * @return Message response from running the command.
     * @throws Exception Throw Exception if index is incorrect
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Duplicate duplicate) throws Exception {
        int index;
        String result;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidDeleteException(splitTask[0]);
        }

        if (index > 0 && index <= taskList.size()) {
            result = ui.printDeleteTask(taskList.get(index - 1), taskList.size() - 1);
            taskList.remove(index - 1);
        } else {
            throw new DukeInvalidMarkException(Integer.toString(index));
        }
        storage.writeFile(taskList);

        return result;
    }
}
