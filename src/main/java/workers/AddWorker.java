package workers;

import java.util.ArrayList;

import duke.IrisException;
import duplicate_detectors.DeadlineDuplicateDetector;
import duplicate_detectors.EventDuplicateDetector;
import duplicate_detectors.TodoDuplicateDetector;
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
                throw new IrisException("☹ OOPS!!! The content of a command cannot be empty.");
            }

            String content = inputParts[1];
            switch (inputParts[0]) {
            case "todo":
                TodoDuplicateDetector todoDuplicateDetector = new TodoDuplicateDetector();
                if (todoDuplicateDetector.checkDuplicates(content, taskList)) {
                    return "Error: This todo task already exists.";
                }
                newTask = new Todo(content);
                break;
            case "deadline": {
                String[] contentParts = content.split(" /by ", 2);
                String description = contentParts[0];
                String dateOfDeadline = contentParts[1];
                DeadlineDuplicateDetector deadlineDuplicateDetector = new DeadlineDuplicateDetector();
                if (deadlineDuplicateDetector.checkDuplicates(description, dateOfDeadline, taskList)) {
                    return "Error. This deadline task already exists.";
                }
                newTask = new Deadline(description, dateOfDeadline);
                break;
            }
            case "event": {
                String[] contentParts = content.split(" /from ", 2);
                String[] dateParts = contentParts[1].split(" /to ");
                String description = contentParts[0];
                String dateOfFrom = dateParts[0];
                String dateOfTo = dateParts[1];
                EventDuplicateDetector eventDuplicateDetector = new EventDuplicateDetector();
                if (eventDuplicateDetector.checkDuplicates(description, dateOfFrom, dateOfTo, taskList)) {
                    return "Error. This event task already exists.";
                }
                newTask = new Event(description, dateOfFrom, dateOfTo);
                break;
            }
            default:
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
