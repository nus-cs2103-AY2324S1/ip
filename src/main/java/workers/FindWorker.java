package workers;

import java.util.ArrayList;

import tasks.Task;

/**
 * A specialised worker class that does the find command.
 */
public class FindWorker extends TaskWorker {
    /**
     * This method lists out the tasks in the list of tasks that contain the target string.
     * @param inputParts
     * @param taskList
     */
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList) {
        assert inputParts != null;
        int count = 1;
        String keyword = inputParts[1].toLowerCase();
        String output = "Here are the matching tasks in your list:\n";;

        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                output += count + ". " + task + "\n";
            }
            count++;
        }

        if (output == "Here are the matching tasks in your list:\n") {
            return "No matching tasks found";
        }

        return output;
    }
}
