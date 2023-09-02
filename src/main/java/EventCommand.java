import java.io.IOException;

public class EventCommand extends Command {
    String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        taskList.addEvent(fullCommand);
    }
}
