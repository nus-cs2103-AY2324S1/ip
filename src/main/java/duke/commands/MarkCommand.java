package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class MarkCommand extends Command{

    /**
     * Marks the task indicated as completed
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return response if successfully marked.
     * @throws DukeException when there is no task provided to mark.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("Please indicate which task to mark!");
        } else {
            int taskNo = Integer.parseInt(parsedInput[1]);
            message = TaskList.mark(taskNo);
            assert message != null : "response should be given";
        }

        return message;
    }
}
