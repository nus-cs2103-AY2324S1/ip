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
     *
     * @return A string representing the tasks in the task list, prefixed by their index.
     *         If there are no tasks, it returns a string indicating that there are no
     *         tasks in the task list.
     */
    @Override
    public String execute() {
        return uiService.formatTaskList(dukeBot.getTaskList());
    }
}
