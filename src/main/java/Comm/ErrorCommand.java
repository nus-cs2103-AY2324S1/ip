package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

public class ErrorCommand extends Command{

    public ErrorCommand(){
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}