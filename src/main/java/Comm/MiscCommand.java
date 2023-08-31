package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

public class MiscCommand extends Command{

    public MiscCommand(){
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.misc();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}