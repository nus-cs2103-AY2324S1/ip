package dude.command;

import dude.note.NoteList;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Represents a command that is not defined in Dude.
 */
public class UnknownCommand extends Command {

    private final String input;

    public UnknownCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to tell users the command is unknown.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = ui.showUnknownCommand(this.input) + "\n";
        return output;
    }
}
