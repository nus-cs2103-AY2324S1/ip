package duchess.command;

import duchess.DuchessException;
import duchess.Parser;
import duchess.TaskList;
import duchess.TaskStatus;
import duchess.Ui;

/**
 * Class representing a MarkTask command.
 */
public class MarkTaskCommand extends Command {
    /**
     * Marks a task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";
        try {
            int index = Parser.parseMarkTaskCommand(userInput);
            index -= 1; // 1-indexing for user-facing side

            // Within bounds
            if (index < 0 || index >= tasks.size()) {
                // TODO: Make this a different exception type
                response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
                return response;
            }

            tasks.getTask(index).changeStatus(TaskStatus.MARKED);
            response += Ui.duchessFormat("Task has been marked!! (＾▽＾)");
            response += Ui.duchessFormat(String.format("%d: %s", index + 1, tasks.getTask(index).toString()));

        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("mark [task number]");
        }
        return response;

    }
}
