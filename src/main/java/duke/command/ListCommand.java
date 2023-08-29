package duke.command;
import duke.task.*;
import duke.helper.*;
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        tasks.display();
    }
}
