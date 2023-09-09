package martin.commands;

import martin.task.Task;

import java.util.ArrayList;

public class ListCommand implements Command {

    private ArrayList<Task> tasks;

    public ListCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
    * Lists all tasks currently in the list.
    * @return String The list of all tasks.
    */
    @Override
    public String execute() {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i)).append("\n");
        }

        return result.toString().trim();
    }
}
