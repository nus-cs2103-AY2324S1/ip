import java.io.IOException;

public class DeleteCommand extends Command{
    public DeleteCommand(String input) {
        super.isExit = false;
        super.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.delete(input, storage);
    }
}
