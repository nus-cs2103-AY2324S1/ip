package Commands;
import Utilities.*;
import Utilities.Exceptions.InvalidTaskIndexException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            System.out.println(Ui.divider + "\n" + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i));
            }
            System.out.println(Ui.divider + "\n");
        } catch (InvalidTaskIndexException ex) {
            System.out.println(ex);
        }
    }
}
