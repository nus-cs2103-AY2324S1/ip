package duke.command;
import java.io.IOException;
import duke.task.*;
import duke.helper.*;
public class DeleteCommand extends Command{
    /**
     * tasknum indicates the index in the TaskList class to delete
     */
    int tasknum;

    /**
     * Normal Constructor for the DeleteCommand
     * @param tasknum indicates the index in the TaskList to delete
     */
    public DeleteCommand(int tasknum) {
        this.tasknum = tasknum;
    }

    /**
     * Executes the DeleteCommand to remove task to TaskList using the parameters
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     * @throws DukeException throws a DukeException which indicates no desc if no text left
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.deleteTask(tasknum);
            store.save(tasks);
        }
        catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
