package duke.command;

import duke.exception.ArgumentMustBeNumException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** Represents an unmark command. Unmarks a task from the task list based on the number. */
public class UnmarkCommand extends Command {
    private String s;

    /**
     * Returns an UnmarkCommand
     *
     * @param out Printer to print output
     * @param taskList Tasklist to update
     * @param savefile File to write task list to
     * @param s Index of task to mark
     * @return an UnmarkCommand with the wrapped info
     */
    public UnmarkCommand(Printer out, TaskList taskList, FileIO savefile, String s) {
        super(out, taskList, savefile);
        this.s = s;
    }

    /**
     * The unmark action
     *
     * @throws ArgumentMustBeNumException when s is not a number
     */
    @Override
    public void action() {
        Task task;
        try {
            task = taskList.getTask(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            throw new ArgumentMustBeNumException(UNMARK);
        }
        task.unmark();

        out.print("Ok, I've marked this task as not done yet", task.toString());
        save();
    }
}
