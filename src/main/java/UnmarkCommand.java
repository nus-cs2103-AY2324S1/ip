import java.io.IOException;

public class UnmarkCommand extends Command{
    public UnmarkCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.unmark(input, storage);
    }
}
