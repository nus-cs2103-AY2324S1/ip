package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Duke.DukeException;
public class MarkTaskCommand implements Command {
    private int id;
    public MarkTaskCommand(int id) {
        this.id = id;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(id);
            ui.printTaskMarkedMessage(tasks.getTask(id));
        } catch (RuntimeException e) {
            throw new DukeException("\tIndex out of bounds. There are " + tasks.getSize() + " tasks currently.");
        }
    }
}
