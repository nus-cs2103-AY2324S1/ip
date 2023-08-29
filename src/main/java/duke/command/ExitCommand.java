package duke.command;
import duke.task.*;
import duke.helper.*;
public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {

    }

    @Override
    public boolean isExit() {
        return true;
    }

}
