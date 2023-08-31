package duke.parse.command;

import duke.Duke;

public class ExitCommand implements Command {
    public ExitCommand() {
    }

    @Override
    public boolean execute(Duke bot) {
        bot.exit();
        return false;
    }
}
