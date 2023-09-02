package duke.commands;

import duke.Duke;
import duke.service.UiService;

import java.util.List;

public abstract class Command {
    protected Duke dukeBot;
    protected UiService uiService;

    protected Command(Duke dukeBot, UiService uiService) {
        this.dukeBot = dukeBot;
        this.uiService = uiService;
    }

    public abstract void execute();

    public boolean isExit() {
        return false;
    }
}
