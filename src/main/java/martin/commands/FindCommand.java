package martin.commands;

import martin.Ui;
import martin.task.Task;

import java.util.ArrayList;

public class FindCommand implements Command {

    private Ui ui;
    private String command;
    private ArrayList<Task> tasks;

    public FindCommand(String command, ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.command = command;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = command.substring(5);

        for (Task task : tasks) {
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + matchingTasks.get(i).toString());
        }
        ui.showLine();
    }
}
