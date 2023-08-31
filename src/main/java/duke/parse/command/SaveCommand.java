package duke.parse.command;

import duke.Duke;

public class SaveCommand implements Command {
    public SaveCommand() {}

    @Override
    public boolean execute(Duke bot) {
        bot.saveData();
        return true;
    }
}
