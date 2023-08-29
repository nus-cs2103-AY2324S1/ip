package duke.command;

import duke.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList<Task> findTasks(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().matches(".*\\b" + keyword.toLowerCase() + "\\b.*")) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = findTasks(tasks, keyword);

        if (!matchingTasks.isEmpty()) {
            Ui.showHorizontalLine();
            System.out.println("    Here are the matching tasks in your list:");

            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                System.out.println("    " + (i + 1) + "." + task.toString());
            }

            Ui.showHorizontalLine();
        } else {
            Ui.showHorizontalLine();
            System.out.println("    No tasks match the keyword: " + keyword);
            Ui.showHorizontalLine();
        }
    }
}
