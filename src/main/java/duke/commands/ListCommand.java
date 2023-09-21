package duke.commands;

import duke.ui.Ui;

public class ListCommand extends Command{

    /**
     * Lists the current tasks in the list.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return the String of current tasks.
     */
    @Override
    public String execute(String[] parsedInput, String message) {
        message = Ui.listTasks();
        assert message != null : "response should be given";
        return message;
    }
}
