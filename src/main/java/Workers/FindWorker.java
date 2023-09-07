package Workers;

import Tasks.Task;

import java.util.ArrayList;

public class FindWorker extends TaskWorker {
    /**
     * This method lists out the tasks in the list of tasks that contain the target string.
     * @param inputParts
     * @param taskList
     */
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList) {
        int count = 1;
        String keyword = inputParts[1].toLowerCase();
        String output = "Here are the matching tasks in your list:\n";

        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                output += count++ + ". " + task + "\n";
            }
        }

        return output;
    }
}
