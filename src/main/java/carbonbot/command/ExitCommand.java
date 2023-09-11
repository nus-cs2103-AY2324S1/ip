package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;

/**
 * The command will close the Ui and exit the chat bot.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand object with isExit set to true.
     */
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bufferMessage("Bye. Hope to see you again soon!");
        assert this.isExit();
    }
}
