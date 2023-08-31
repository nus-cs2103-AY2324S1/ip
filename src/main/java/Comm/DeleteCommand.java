package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Tasks;
public class DeleteCommand extends Command{

    private String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        try {
            int index = Integer.parseInt(userInput.substring(7));
            Tasks deleted = t.get(index - 1);
            t.remove(index - 1);
            FileHandler.writeTasksToFile(t);
            ui.delete(deleted);
        } catch (IndexOutOfBoundsException e) {
            ui.IOOBExceptionMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
