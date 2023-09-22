package workers;

import java.util.ArrayList;

import duke.IrisException;
import task_creator.CreateDeadlineTask;
import task_creator.CreateEventTask;
import task_creator.CreateTask;
import task_creator.CreateTodoTask;
import tasks.Task;

/**
 * A specialised worker class that does the add command.
 */
public class AddWorker extends TaskWorker {
    @Override
    public String work(String[] inputParts, ArrayList<Task> taskList) {
        assert inputParts != null;
        try {
            if (inputParts.length < 2) {
                throw new IrisException("☹ OOPS!!! The content of a command cannot be empty.");
            }
            String content = inputParts[1];
            CreateTask creator;
            Task newTask;
            switch (inputParts[0]) {
            case "todo":
                creator = new CreateTodoTask();
                newTask = creator.create(content, taskList);
                break;
            case "deadline":
                creator = new CreateDeadlineTask();
                newTask = creator.create(content, taskList);
                break;
            case "event":
                creator = new CreateEventTask();
                newTask = creator.create(content, taskList);
                break;
            default:
                throw new IrisException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            assert creator != null;
            taskList.add(newTask);
            return "Got it. I've added this task:\n" + newTask
                    + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
        } catch (IrisException e) {
            return e.getMessage();
        }
    }
}

