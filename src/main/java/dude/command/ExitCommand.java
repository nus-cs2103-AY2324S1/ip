package dude.command;

import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Represents a command that exits the programme.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes the command to exit the programme.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = ui.showFarewell() + "\n";
        return output;
    }
}
