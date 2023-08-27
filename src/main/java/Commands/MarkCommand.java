package Commands;
import Utilities.Exceptions.InvalidTaskIndexException;
import Utilities.Storage;
import Utilities.TaskList;

public class MarkCommand extends Command {
    private int markIdx;

    public MarkCommand(int markIdx) {
        this.markIdx = markIdx;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.getTask(this.markIdx - 1).markDone();
            System.out.println("Nice! I've marked this task as done");
            System.out.println("\t" + taskList.getTask(this.markIdx - 1));
        } catch (InvalidTaskIndexException ex) {
            System.out.println(ex);
        }

    }
}
