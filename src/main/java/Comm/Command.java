package Comm;
import java.io.File;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

public abstract class Command {

    private boolean isExit = false;

    public abstract void execute(TaskList t, Ui ui, FileHandler f);

    public abstract boolean isExit();

}
