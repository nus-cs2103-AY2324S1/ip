package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;


public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
}
