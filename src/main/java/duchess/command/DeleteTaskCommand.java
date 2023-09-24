package duchess.command;

import duchess.DuchessException;
import duchess.Parser;
import duchess.Task;
import duchess.TaskList;
import duchess.Ui;

/**
 * Class representing a DeleteTask command.
 */
public class DeleteTaskCommand extends Command {
    /**
     * Deletes a task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";

        try {
            int index = Parser.parseDeleteTaskCommand(userInput);
            index -= 1; // 1-indexing for user-facing side

            if (index < 0 || index >= tasks.size()) {
                response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
                return response;
            }

            Task task = tasks.removeTask(index);
            response += Ui.duchessFormat("Deleted successfully!! d(*⌒▽⌒*)b");
            response += Ui.duchessFormat(String.format("%d: %s", index + 1, task.toString()));
            response += Ui.duchessFormat("");
            response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", tasks.size()));

            return response;

        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("delete [task number]");
        }

        return response;
    }
}
