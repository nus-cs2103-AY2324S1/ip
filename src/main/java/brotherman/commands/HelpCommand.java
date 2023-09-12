package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;
public class HelpCommand extends Command {
    /**
     * Constructor for Command
     *
     * @param isExit Whether the command is an exit command
     */
    public HelpCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
