package martin.commands;

import martin.Ui;
import martin.task.Task;

import java.util.ArrayList;

public class ListCommand implements Command {

    private Ui ui;
    private ArrayList<Task> tasks;

    public ListCommand(ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.tasks = tasks;
    }

    /**
    * Prints all tasks currently in the list.
    */
    @Override
    public void execute() {
        ui.showLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        ui.showLine();
    }
}
