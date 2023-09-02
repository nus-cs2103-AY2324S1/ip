package bob.command;
import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingIndexException;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super.input = input;
        super.isExit = false;
    }

    /**
     * Deletes task based on user input.
     * Handles exceptions that may arise from bad user input
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] deleteIndex = input.split(" ");
            if (deleteIndex.length == 1) {
                throw new MissingIndexException();
            }

            int index = 0;
            try {
                index = Integer.parseInt(deleteIndex[1]);
            } catch (NumberFormatException e) {
                throw new MissingIndexException();
            }

            ui.stringFormat(tasks.deleteTask(index));
            storage.write(tasks.lst);

        } catch (IOException e) {
            ui.showLoadingError();
        } catch (MissingIndexException e) {
            ui.stringFormat(new String[]{e.message});
        } catch (IndexOutOfBoundsException e) {
            ui.stringFormat(new String[]{"Index provided is wrong!"});
        }
    }
}
