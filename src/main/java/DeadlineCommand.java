import java.io.IOException;

public class DeadlineCommand extends Command {

    String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        taskList.addDeadline(fullCommand);
    }
}
