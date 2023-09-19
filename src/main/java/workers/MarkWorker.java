package workers;

import java.util.ArrayList;

import duke.IrisException;
import tasks.Task;

/**
 * A specialised worker class that does the mark command.
 */
public class MarkWorker extends TaskWorker {
    /**
     * This method marks the Task at the specified index as done.
     * @param inputParts
     * @param taskList
     * @param markAsDone
     */
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList, boolean markAsDone) {
        assert  inputParts != null;
        int index = Integer.parseInt(inputParts[1]) - 1;
        try {
            if (index > taskList.size() || index < 0) {
                throw new IrisException("Task does not exist. Please enter a valid index number.");
            }
            Task task = taskList.get(index);
            if (markAsDone) {
                task.markDone();
                return "Nice! I've marked this task as done:\n" + task;
            } else {
                task.markUndone();
                return "OK, I've marked this task as not done yet:\n" + task;

            }
        } catch (IrisException e) {
            return e.getMessage();
        }
    }
}
