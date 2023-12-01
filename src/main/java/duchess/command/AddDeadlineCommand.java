package duchess.command;

import duchess.Deadline;
import duchess.DuchessException;
import duchess.Parser;
import duchess.TaskList;
import duchess.Ui;

/**
 * Class representing an AddDeadline command.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Adds a Deadline task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";
        try {
            Deadline deadline = Parser.parseDeadlineCommand(userInput);

            assert deadline != null : "Task is null";

            tasks.addTask(deadline);

            response += Ui.duchessFormat("Added successfully!! d(*⌒▽⌒*)b");
            response += Ui.duchessFormat(String.format("%d: %s", tasks.indexOf(deadline) + 1, deadline.toString()));
            response += Ui.duchessFormat("");
            response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", tasks.size()));

            return response;
        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("deadline [name] /by [year-month-date]");
        }

        return response;
    }
}
