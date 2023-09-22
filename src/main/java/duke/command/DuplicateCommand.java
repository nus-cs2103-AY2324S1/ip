package duke.command;

import duke.Duplicate.Duplicate;
import duke.exception.DukeDuplicatesCommandException;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to handle duplicates.
 *
 * @author marioalvaro
 */
public class DuplicateCommand extends Command {

    private String[] splitTask;

    /**
     * Constructor for DuplicateCommand.
     *
     * @param splitTask array of String that contains the keyword
     */
    public DuplicateCommand(String[] splitTask) {
        this.splitTask = splitTask;

    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Duplicate duplicate) throws DukeException {
        String commandWord = splitTask[1];

        if (commandWord.equals("show")) {
            return ui.printDuplicatesMode(duplicate);
        }

        boolean isOn = commandWord.equals("on");
        boolean isText = commandWord.equals("text");
        boolean isOff = commandWord.equals("off");
        if (!(isOn || isOff || isText)) {
            throw new DukeDuplicatesCommandException("");
        }

        duplicate.updateCheckDuplicates(splitTask[1]);

        return ui.printUpdateDuplicatesMode();
    }
}
