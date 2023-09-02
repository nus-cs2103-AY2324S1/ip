package duke.commands;

import duke.Duke;
import duke.service.UiService;

/**
 * Represents a command to list all tasks in the Duke application.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     */
    public ListCommand(Duke dukeBot, UiService uiService) {
        super(dukeBot, uiService);
    }

    /**
     * Executes the command to display the list of tasks.
     */
    @Override
    public void execute() {
        uiService.printTaskList(dukeBot.getTaskList());
    }
}
