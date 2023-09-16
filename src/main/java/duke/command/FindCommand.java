package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * The FindCommand represents a command
 * to find a specific task given a word
 */
public class FindCommand extends Command {

    private String text;

    /**
     * Sets the isExit to false and add the
     * text to find.
     *
     * @param text the text to find.
     */
    public FindCommand(String text) {
        super(false);
        this.text = text;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> found = tasks.find(text);
        return (ui.showFoundTask(found));
    }
}
