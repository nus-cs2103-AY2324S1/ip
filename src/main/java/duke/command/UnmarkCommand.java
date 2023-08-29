package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command that un-marks a specific task.
 */
public class UnmarkCommand implements Command {

    /**
     * Identifies the index of the task to be un-marked
     * with the last message. Marks it as not done.
     *
     * @param tasks The task list containing the tasks.
     * @param ui    The user interface used to retrieve the last message.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            String msg = ui.getLastMsg();
            String[] words = msg.toLowerCase().split("\\s+");
            int index = Integer.parseInt(words[1]) - 1;
            tasks.unmark(index);
            ui.respond("OK, I've marked this task as not done yet:" + "\n" + tasks.get(index));
        } catch (Exception e) {
            throw new DukeException("Wrong index. Try checking your list first.");
        }
        return false;
    }
}
