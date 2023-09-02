package crusader.command;

import crusader.TaskList;
import crusader.Ui;

/**
 * Command used to exit the bot.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        // empty
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
