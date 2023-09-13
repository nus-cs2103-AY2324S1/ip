package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class DisplayCommand extends Command {

    public DisplayCommand(String input) {
        super.input = input;
    }

    /**
     * Displays list
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] strSplit = input.split(" ");
        String priority = "";
        try {
            if (strSplit.length > 0) {
                priority = strSplit[1].split("/")[1];
            }
            return ui.stringFormat(tasks.displayList(priority));
        } catch (Exception e) {
            return ui.stringFormat(tasks.displayList(priority));
        }
    }
}
