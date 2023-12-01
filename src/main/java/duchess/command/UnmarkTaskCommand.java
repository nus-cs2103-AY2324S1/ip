package duchess.command;

import duchess.DuchessException;
import duchess.Parser;
import duchess.TaskList;
import duchess.TaskStatus;
import duchess.Ui;

/**
 * Class representing a UnmarkTask command.
 */
public class UnmarkTaskCommand extends Command {
    /**
     * Unmarks a task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";

        try {
            int index = Parser.parseUnmarkTaskCommand(userInput);
            index -= 1; // 1-indexing for user-facing side

            if (index < 0 || index >= tasks.size()) {
                response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
                return response;
            }

            tasks.getTask(index).changeStatus(TaskStatus.UNMARKED);
            response += Ui.duchessFormat("Task has been unmarked!! (＾▽＾)");
            response += Ui.duchessFormat(String.format("%d: %s", index + 1, tasks.getTask(index).toString()));

        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("unmark [task number]");
        }

        return response;
    }
}
