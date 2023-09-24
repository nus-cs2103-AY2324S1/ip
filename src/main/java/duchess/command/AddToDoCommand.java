package duchess.command;

import duchess.DuchessException;
import duchess.Parser;
import duchess.TaskList;
import duchess.ToDo;
import duchess.Ui;

/**
 * Class representing an AddToDo command.
 */
public class AddToDoCommand extends Command {
    /**
     * Adds a ToDo task.
     *
     * @param userInput - the user input.
     * @param tasks       the list of tasks.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        String response = "";
        try {
            ToDo todo = Parser.parseToDoCommand(userInput);

            assert todo != null : "Task is null";

            tasks.addTask(todo);

            response += Ui.duchessFormat("Added successfully!! d(*⌒▽⌒*)b");
            response += Ui.duchessFormat(String.format("%d: %s", tasks.indexOf(todo) + 1, todo.toString()));
            response += Ui.duchessFormat("");
            response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", tasks.size()));

            return response;
        } catch (DuchessException e) {
            response += Ui.duchessFormat(e.getMessage());
            response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
            response += Ui.duchessFormat("todo [name]");
        }

        return response;
    }
}
