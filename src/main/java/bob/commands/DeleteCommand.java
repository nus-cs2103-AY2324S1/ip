package bob.commands;
import java.io.IOException;
import bob.*;
import bob.exceptions.*;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super.input = input;
        super.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] deleteIndex = input.split(" ");
            if (deleteIndex.length == 1) {
                throw new MissingIndexException();
            }
            int index = 0;
            try {
                index = Integer.parseInt(deleteIndex[1]);
            } catch (NumberFormatException e) {
                throw new MissingIndexException();
            }
            ui.stringFormat(tasks.deleteTask(index));
            storage.write(tasks.lst);
        } catch (IOException e) {
            ui.showLoadingError();
        } catch (MissingIndexException e) {
            ui.stringFormat(new String[]{e.message});
        } catch (IndexOutOfBoundsException e) {
            ui.stringFormat(new String[]{"Index provided is wrong!"});
        }
    }
}
