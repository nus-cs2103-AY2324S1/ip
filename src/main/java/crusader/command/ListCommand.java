package crusader.command;

import crusader.TaskList;
import crusader.Ui;

/**
 * Command used to show all tasks in the bot.
 */
public class ListCommand extends Command {
    public ListCommand() {
        // nothing
    }

    @Override
    public String execute(Ui ui, TaskList taskList) {
        return String.format(
                "Here are your tasks:\n%s",
                taskList.toString());
    }
}
