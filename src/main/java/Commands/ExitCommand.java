package Commands;
import Utilities.Ui;
import Utilities.Storage;
import Utilities.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Storage storage) {
        storage.writeTaskFile(tasklist);
        System.out.println(Ui.closing);
        System.exit(0);
    }
}
