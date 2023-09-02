package didier.command;

import didier.Storage;
import didier.TaskList;

/**
 * The ExitCommand encapsulates the logic of what occurs when the user tries to exit the interaction
 * with the bot.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) {
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) {
        return "Goodbye! If you need more help in the future don't hesitate to ask me.";
    }
}
