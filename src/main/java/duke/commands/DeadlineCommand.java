package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.TaskList;

public class DeadlineCommand extends Command {

    /**
     * Adds new deadline to task list.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return success message upon successful adding of deadline.
     * @throws DukeException when no description or
     *      no deadline is provided for the deadline.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("Please provide a description for this deadline! (>_<)");
        } else {
            message = addDeadline(parsedInput[1], message);
        }
        return message;
    }

    public String addDeadline(String details, String msg) throws DukeException {
        String[] deadTask = details.split("/by");
        if (deadTask.length == 1) {
            throw new DukeException("Please provide a deadline! (>_<)");
        } else if (deadTask[0].equals("")) {
            throw new DukeException("Please provide a description! (>_<)");
        } else {
            String deadDescription = deadTask[0];
            String deadTime = deadTask[1];
            assert deadTime != null : "there should be a deadline";
            deadTime = deadTime.trim();
            Deadline deadlineTask = new Deadline(deadDescription, deadTime);
            msg = TaskList.add(deadlineTask, "deadline");
            assert msg != null : "response should be given";
        }

        return msg;
    }
}
