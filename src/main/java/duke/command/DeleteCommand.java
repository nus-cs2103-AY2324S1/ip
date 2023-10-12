package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to delete task at specified index. */
public class DeleteCommand implements Command {
    private final String indexString;

    /**
     * Initialize delete command with specified index of task list.
     *
     * @param indexString Index to be deleted.
     */
    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    /**
     * Deletes task at specified index. Attempts to parse index from String to integer.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     * @throws InvalidParametersException Throws error if unable to parse from String to Integer successfully.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        try {
            int index = Integer.parseInt(indexString) - 1;

            taskList.remove(index);
            storage.save(taskList.getTasks());
            setOutput("Task at index " + (index + 1) + " removed successfully.");
            taskList.printSize();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            setOutput(taskList.print());
            throw new InvalidParametersException("Insert an integer from the list");
        }
    }

    /**
     * Replaces existing string in Output with a new string.
     *
     * @param string String to be replaced.
     */
    private void setOutput(String string) {
        OUTPUT.delete(0, OUTPUT.length());
        OUTPUT.append(string);
    }
}
