package duke.command;

import duke.*;

import java.io.IOException;

/**
 * Executes the command to sort the TaskList based off Tag then by Date.
 */
public class SortCommand implements Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {

        try {
            list.sort(new TaskTagComparator());
            list.sort(new TaskDateComparator());

            storage.appendToFile(text + "\n");
            ui.buildMessage("Your task list is sorted as requested, sir.");
            return ui.sendMessage();
        } catch (IOException e) {
            ui.buildMessage("Something went wrong: " + e.getMessage() + " \n");
            return ui.sendMessage();
        }
    }
}
