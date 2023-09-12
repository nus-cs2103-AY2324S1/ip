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
    public String execute(Ui ui, TaskList taskList) {
        return "Bye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
