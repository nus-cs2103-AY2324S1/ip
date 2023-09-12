package workers;

import java.util.ArrayList;

import duke.IrisException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * A specialised worker class that does the add command.
 */
public class AddWorker extends TaskWorker {
    /**
     * This method adds a Task to the given ArrayList.
     * @param inputParts
     * @param taskList
     */
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList) {
        try {
            Task newTask;
            if (inputParts.length < 2) {
                throw new IrisException("☹ OOPS!!! The content of a command cannot be empty.");
            }

            String content = inputParts[1];
            if (inputParts[0].equals("todo")) {
                newTask = new Todo(content);
            } else if (inputParts[0].equals("deadline")) {
                String[] contentParts = content.split(" /by ", 2);
                String description = contentParts[0];
                String dateOfDeadline = contentParts[1];
                newTask = new Deadline(description, dateOfDeadline);
            } else if (inputParts[0].equals("event")) {
                String[] contentParts = content.split(" /from ", 2);
                String[] dateParts = contentParts[1].split(" /to ");
                String description = contentParts[0];
                String dateOfFrom = dateParts[0];
                String dateOfTo = dateParts[1];
                newTask = new Event(description, dateOfFrom, dateOfTo);
            } else {
                throw new IrisException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            taskList.add(newTask);
            return "Got it. I've added this task:\n" + newTask
                    + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
        } catch (IrisException e) {
            return e.getMessage();
        }
    }
}
