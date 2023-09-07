package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that allows the user to exit the app.
 */
public class ExitCommand extends Command {
    /**
     * SHows the goodbye message to the user.
     * @param taskList the existing task list of the user.
     * @param ui the ui that handles successful/unsuccessful messages
     */
    public String execute(TaskList taskList, Ui ui){
        try {
            new Storage("data/duke.txt").update(taskList, "data/duke.txt");
        } catch (DukeException e){
            return e.getMessage();
        }
        return ui.showExitMessage();
    }
}
