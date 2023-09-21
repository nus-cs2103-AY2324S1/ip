package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {

    /**
     * Deletes a task from the task list.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return success message upon successful deletion of the task.
     * @throws DukeException when the task index given is
     * larger than the total number of tasks in the list.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("You do not have that many tasks! (⋟﹏⋞)");
        } else {
            int target = Integer.parseInt(parsedInput[1]);
            message = TaskList.delete(target);
            assert message != null : "response should be given";
        }

        return message;
    }
}
