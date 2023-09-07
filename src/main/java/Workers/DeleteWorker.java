package Workers;

import Tasks.Task;
import duke.IrisException;

import java.util.ArrayList;

public class DeleteWorker extends TaskWorker {
    /**
     * This method deletes the Task at the specified index.
     * @param inputParts
     * @param taskList
     */
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList) {
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            if (index > taskList.size()) {
                throw new IrisException("☹ OOPS!!! Unable to delete non-existent task");
            }
            Task removedTask = taskList.remove(index);
            return "Noted. I've removed this task:\n" + removedTask +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (IrisException e) {
            return e.toString();
        }
    }
}
