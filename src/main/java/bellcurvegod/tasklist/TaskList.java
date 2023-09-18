package bellcurvegod.tasklist;

import java.util.ArrayList;

import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.gui.Gui;
import bellcurvegod.task.Deadline;
import bellcurvegod.task.Event;
import bellcurvegod.task.Task;
import bellcurvegod.task.Todo;

/**
 * Encapsulates the taskList.
 */
public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int numOfTasks = 0;

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void incrementNumOfTasks() {
        numOfTasks++;
    }

    /**
     * Adds a task to the storage.
     *
     * @param input Description of the task to be added.
     * @throws InvalidCommandException   If the input does not start with any of the Task type.
     * @throws EmptyDescriptionException If description is missing.
     */
    public static String addTask(String input) throws InvalidCommandException, EmptyDescriptionException {
        String cmd = input.split(" ")[0];
        Task newTask = null;

        if (!(cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event"))) {
            throw new InvalidCommandException("You have entered an invalid command word!\n"
                + "To add a new Task, use \"todo\", \" deadline\", or \"event\".\n");
        }

        if (cmd.equals("deadline")) {
            newTask = Deadline.generateDeadlineFromInput(input);
        } else if (cmd.equals("event")) {
            newTask = Event.generateEventFromInput(input);
        } else {
            newTask = Todo.generateTodoFromInput(input);
        }

        if (!newTask.getDescription().equals("__Faulty")) {
            tasks.add(newTask);
            numOfTasks++;
            assert tasks.contains(newTask) : "The new task should be in the taskList.";
            return Gui.getAddTaskMessage(newTask, numOfTasks);
        } else {
            return Gui.getWrongDateFormatMessage();
        }
    }

    /**
     * Deletes the task from the list.
     *
     * @param task Task to be deleted.
     */
    public static String delete(Task task) {
        tasks.remove(task);
        numOfTasks--;

        assert !tasks.contains(task) : "The removed task should no longer be in the taskList";

        return Gui.getDeleteTaskMessage(task, numOfTasks);
    }
}
