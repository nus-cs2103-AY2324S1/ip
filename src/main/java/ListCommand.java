import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand() {}
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showListMsg(tasks.showList());
    }
}
