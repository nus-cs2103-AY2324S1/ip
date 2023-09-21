package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class UnmarkCommand extends Command{

    /**
     * Unmarks the task indicated as uncompleted
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return response if successfully unmarked.
     * @throws DukeException when there is no task provided to unmark.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("Please indicate which task to unmark!");
        } else {
            int taskNo = Integer.parseInt(parsedInput[1]);
            message = TaskList.unmark(taskNo);
            assert message != null : "response should be given";
        }

        return message;
    }
}
