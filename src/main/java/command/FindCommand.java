package command;

import ui.Ui;

import storage.TaskList;
import storage.FileHandler;

/**
 * A find command to query task with a keyword.
 */
public class FindCommand extends Command{

    String keyword;

    /**
     * Constructs an `FindCommand` object.
     */
    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param t  The list of the task to match.
     * @param ui The user interface.
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.find(t.findKeyword(keyword));
    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}