package duke.commands;

import duke.Duke;
import duke.service.UiService;

import java.util.List;

public class ListCommand extends Command {
    public ListCommand(Duke dukeBot, UiService uiService) {
        super(dukeBot, uiService);
    }

    @Override
    public void execute() {
        uiService.printTaskList(dukeBot.getTaskList());
    }
}
