import java.io.IOException;

public class MarkCommand extends Command {
    public MarkCommand(String input) {
        super.input = input;
        super.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] markIndex = input.split(" ");
            if (markIndex.length == 1) {
                throw new MissingIndexException();
            }
            int index = 0;
            try {
                index = Integer.parseInt(markIndex[1]);
            } catch (NumberFormatException e) {
                throw new MissingIndexException();
            }
            boolean doneOrNot = true;
            if (input.contains("unmark")) {
                doneOrNot = false;
            }
            ui.stringFormat(tasks.markDoneOrNot(index, doneOrNot));
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
