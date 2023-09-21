package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class TodoCommand extends Command {

    /**
     * Adds new todo to task list.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return success message upon successful adding of todo.
     * @throws DukeException when no description is provided for the todo.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1 || parsedInput[1].equals("")) {
            throw new DukeException("Please provide a description for this todo! (⋟﹏⋞)");
        } else {
            String todoTask = parsedInput[1];
            Todo newTodo = new Todo(todoTask);
            message = TaskList.add(newTodo, "todo");
            assert message != null : "response should be given";
        }

        return message;
    }
}
