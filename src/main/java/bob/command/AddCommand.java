package bob.command;
import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class AddCommand extends Command {

    public AddCommand(String input) {
        super.input = input;
        super.isExit = false;
    }

    /**
     * Adds task to list based on user input.
     * Handles exceptions that may arise from bad user input
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.stringFormat(tasks.addToList(input));
            storage.write(tasks.lst);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }
}
