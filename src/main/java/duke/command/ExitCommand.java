package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.messages.Messages;

/**
 * A command that allows the user to exit the app.
 */
public class ExitCommand extends Command {
    /**
     * SHows the goodbye message to the user.
     * @param taskList the existing task list of the user.
     * @param ui the ui that handles successful/unsuccessful messages
     */
    public void execute(TaskList taskList, Ui ui){
        System.out.println(Messages.GOODBYE_MESSAGE.getMessage());
    }
}
