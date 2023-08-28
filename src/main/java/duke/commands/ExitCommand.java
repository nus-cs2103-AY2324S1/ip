package duke.commands;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Storage storage) {
        storage.writeTaskFile(tasklist);
        System.out.println(Ui.closing);
        System.exit(0);
    }
}
