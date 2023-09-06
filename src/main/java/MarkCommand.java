import java.io.IOException;

public class MarkCommand extends Command{
    public MarkCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.mark(input, storage);
    }
}
