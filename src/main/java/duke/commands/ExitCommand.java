package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit the program and write data to the task file.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasklist, Storage storage) {
        storage.writeTaskFile(tasklist);
        System.out.println(Ui.closing);
        System.exit(0);
    }
}
