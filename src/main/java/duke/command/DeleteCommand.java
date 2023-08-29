package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command that deletes the task from the task list using index.
 */
public class DeleteCommand implements Command{

    /**
     * Identifies the index of the task to be deleted
     * with the last message and deletes it.
     *
     * @param tasks The task list containing the tasks.
     * @param ui The user interface used to retrieve the last message.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public  boolean execute(TaskList tasks, Ui ui) throws DukeException {
        try {
            String msg = ui.getLastMsg();
            String[] words = msg.toLowerCase().split("\\s+");
            int index = Integer.parseInt(words[1])-1;
            Task temp = tasks.get(index);
            tasks.remove(index);
            ui.respond("Noted. I've removed this task: "+"\n" + temp +
                    "\n" + "Now you have " + tasks.size() + " tasks in the list");
        } catch (Exception e){
            throw new DukeException("Wrong index. Try checking your list first.");
        }
        return false;
    }
}
