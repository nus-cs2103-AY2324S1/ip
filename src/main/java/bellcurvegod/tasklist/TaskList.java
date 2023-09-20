package bellcurvegod.tasklist;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bellcurvegod.exception.EmptyByTimeException;
import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.EmptyFromTimeException;
import bellcurvegod.exception.EmptyToTimeException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.exception.ToTimeEarlierThanFromTimeException;
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
     * @return AddTaskMessage if the task is added successfully.
     * @throws InvalidCommandException   If the input does not start with any of the Task type.
     * @throws EmptyDescriptionException If description is missing.
     */
    public static String addTask(String input) throws InvalidCommandException, EmptyDescriptionException {
        String cmd = input.split(" ")[0];
        Task newTask;

        if (!(cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event"))) {
            throw new InvalidCommandException("You have entered an invalid command word!\n"
                + "To add a new Task, use \"todo\", \" deadline\", or \"event\".\n");
        }

        try {
            if (cmd.equals("deadline")) {
                newTask = Deadline.generateDeadlineFromInput(input);
            } else if (cmd.equals("event")) {
                newTask = Event.generateEventFromInput(input);
            } else {
                newTask = Todo.generateTodoFromInput(input);
            }
        } catch (EmptyByTimeException | EmptyFromTimeException | EmptyToTimeException
                 | ToTimeEarlierThanFromTimeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Gui.getWrongDateFormatMessage();
        }

        tasks.add(newTask);
        numOfTasks++;
        assert tasks.contains(newTask) : "The new task should be in the taskList.";
        return Gui.getAddTaskMessage(newTask, numOfTasks);
    }

    /**
     * Deletes the task(s) from the list.
     *
     * @param taskParams Task(s) to be deleted.
     * @return DeleteTaskMessage.
     */
    public static String delete(Task... taskParams) {
        for (Task t : taskParams) {
            tasks.remove(t);
            numOfTasks--;
            assert !tasks.contains(t) : "The removed task should no longer be in the taskList";
        }
        return Gui.getDeleteTaskMessage(numOfTasks, taskParams);
    }
}
