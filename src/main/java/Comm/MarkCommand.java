package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Tasks;
public class MarkCommand extends Command{

    private String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        try {
            int index = Integer.parseInt(userInput.substring(5));
            t.get(index - 1).markDone();
            FileHandler.writeTasksToFile(t);
            ui.mark(index);
        } catch (IndexOutOfBoundsException e) {
            ui.IOOBExceptionMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}