package workers;

import java.util.ArrayList;

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
        int index = Integer.parseInt(inputParts[1]) - 1;
        Task task = taskList.get(index);
        if (markAsDone) {
            task.markDone();
            return "Nice! I've marked this task as done:\n" + task;
        } else {
            task.markUndone();
            return "OK, I've marked this task as not done yet:\n" + task;
        }
    }
}
