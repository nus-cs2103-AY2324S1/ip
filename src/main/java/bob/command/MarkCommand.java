package bob.command;
import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingIndexException;

public class MarkCommand extends Command {

    public MarkCommand(String input) {
        super.input = input;
    }

    /**
     * Marks task as done or undone based on user input.
     * Handles exceptions that may arise from bad user input
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] markIndex = input.split(" ");
            if (markIndex.length == 1) {
                throw new MissingIndexException();
            }

            int index = 0;

            try {
                index = Integer.parseInt(markIndex[1]);
            } catch (NumberFormatException e) {
                throw new MissingIndexException();
            }

            boolean doneOrNot = !input.contains("unmark");

            String display = ui.stringFormat(tasks.markDoneOrNot(index, doneOrNot));
            storage.write(tasks.lst);
            return display;

        } catch (IOException e) {
            return ui.showLoadingError();
        } catch (MissingIndexException e) {
            return ui.stringFormat(new String[]{e.message});
        } catch (IndexOutOfBoundsException e) {
            return ui.stringFormat(new String[]{"Index provided is wrong!"});
        }
    }
}
