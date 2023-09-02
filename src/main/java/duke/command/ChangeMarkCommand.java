package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ChangeMarkCommand implements Command {

    private final String indexString;
    private final boolean isMark;

    public ChangeMarkCommand(String indexString, boolean isMark) {
        this.indexString = indexString;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        try {
            int index = Integer.parseInt(indexString) - 1;

            if (isMark) {
                taskList.mark(index);
                storage.save();
                ui.showMessage("Task at index " + (index + 1) + "has been successfully marked");
            } else {
                taskList.unmark(index);
                storage.save();
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
