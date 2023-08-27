package bob.command;
import java.io.IOException;
import bob.*;

public class AddCommand extends Command {
    public AddCommand(String input) {
        super.input = input;
        super.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.stringFormat(tasks.addToList(input));
            storage.write(tasks.lst);
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }
}
