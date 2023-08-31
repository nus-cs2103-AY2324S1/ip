package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

public class UnmarkCommand extends Command{

    private String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        try {
            int index = Integer.parseInt(userInput.substring(7));
            t.get(index - 1).markNotDone();
            FileHandler.writeTasksToFile(t);
            ui.unmark(index);
        } catch (IndexOutOfBoundsException e) {
            ui.IOOBExceptionMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}