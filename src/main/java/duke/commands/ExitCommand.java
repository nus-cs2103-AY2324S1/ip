package duke.commands;

import duke.Duke;
import duke.service.UiService;

public class ExitCommand extends Command {
    public ExitCommand(Duke dukeBot, UiService uiService) {
        super(dukeBot, uiService);
    }

    @Override
    public void execute() {
        // will be handled outside of this class.
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
