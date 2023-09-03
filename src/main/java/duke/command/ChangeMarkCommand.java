package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to change whether the task from mark to unmark (or vice versa) */
public class ChangeMarkCommand implements Command {
    private final String indexString;
    private final boolean isMark;

    /**
     * Initializes change mark command.
     *
     * @param indexString String that contains the index of TaskList.
     * @param isMark Boolean to indicate if the task should be marked or not.
     */
    public ChangeMarkCommand(String indexString, boolean isMark) {
        this.indexString = indexString;
        this.isMark = isMark;
    }

    /**
     * Marks or Unmarks task based on given index. Attempts to parse index from String to integer.
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

            if (isMark) {
                taskList.mark(index);
                storage.save(taskList.getList());
                ui.showMessage("Task at index " + (index + 1) + "has been successfully marked");
            } else {
                taskList.unmark(index);
                storage.save(taskList.getList());
                ui.showMessage("Task at index " + (index + 1) + "has been successfully unmarked");
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            taskList.print();
            throw new InvalidParametersException("Insert an integer from the list");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
