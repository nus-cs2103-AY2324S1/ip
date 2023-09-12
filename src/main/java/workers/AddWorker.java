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
        assert inputParts != null;
        try {
            Task newTask;
            if (inputParts.length < 2) {
                throw new IrisException("☹ OOPS!!! The description of a command cannot be empty.");
            }

            String description = inputParts[1];
            if (inputParts[0].equals("todo")) {
                newTask = new Todo(description);
            } else if (inputParts[0].equals("deadline")) {
                String[] commandParts = description.split("/by ", 2);
                newTask = new Deadline(commandParts[0], commandParts[1]);
            } else if (inputParts[0].equals("event")) {
                String[] commandParts = description.split("/from ", 2);
                String[] eventParts = commandParts[1].split("/to ");
                newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
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
