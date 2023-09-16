package fluke;

import java.util.ArrayList;

import fluke.exceptions.FlukeException;
import fluke.exceptions.TaskDoesNotExistException;
import fluke.tasks.Deadline;
import fluke.tasks.Event;
import fluke.tasks.Task;
import fluke.tasks.Todo;

/**
 * TaskList is a wrapper class that manages the internal list of tasks by implementing
 * various methods related to managing the list of tasks, such as adding and deleting.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an initial list of tasks.
     * @param initialTasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> initialTasks) {
        this.tasks = initialTasks;
    }

    /**
     * Getter for the list of tasks.
     * @return the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the size of the list of tasks.
     * @return size of the list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the task list.
     * @param task task to be added.
     */
    private void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a Todo to the list of tasks
     * @param description description of the task
     * @return the task added.
     * @throws FlukeException if there is an error while adding the task, for instance an invalid description.
     */
    public Task addTodo(String description) throws FlukeException {
        Todo newTodo = new Todo(description);
        addTask(newTodo);
        return newTodo;
    }

    /**
     * Adds a Deadline to the list of tasks.
     * @param description description of the task
     * @param byDate the date which the task is due
     * @return the task added.
     * @throws FlukeException if there is an error while adding the task, for instance an invalid description.
     */
    public Task addDeadline(String description, String byDate) throws FlukeException {
        Task newDeadline = new Deadline(description, byDate);
        addTask(newDeadline);
        return newDeadline;
    }

    /**
     * Adds an Event to the list of tasks.
     * @param description description of the task
     * @param fromDate the date which the event starts
     * @param toDate the date which the event ends
     * @return the task added.
     * @throws FlukeException if there is an error while adding the task, for instance an invalid description.
     */
    public Task addEvent(String description, String fromDate, String toDate) throws FlukeException {
        Task newEvent = new Event(description, fromDate, toDate);
        addTask(newEvent);
        return newEvent;
    }

    /**
     * Deletes a task from the task list.
     * @param index index of the task in the list
     * @return the deleted task.
     * @throws TaskDoesNotExistException if the index given is invalid.
     */
    public Task deleteTask(int index) throws TaskDoesNotExistException {
        // check if task exists
        if (tasks.size() <= index) {
            throw new TaskDoesNotExistException();
        }
        Task taskToBeDeleted = tasks.get(index);
        tasks.remove(index);
        return taskToBeDeleted;
    }

    /**
     * Marks a task in the task list as done.
     * @param index index of the task in the list
     * @return the marked task.
     * @throws TaskDoesNotExistException if the index given is invalid.
     */
    public Task markTaskAsDone(int index) throws TaskDoesNotExistException {
        // check if task exists
        if (tasks.size() <= index) {
            throw new TaskDoesNotExistException();
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Interface to enforce error handling for a Function
     * In this case, it is a function that changes a specific task (via index) in the task list.
     * @param <Integer> the index of the task
     * @param <Task> the task that the function was performed on
     */
    @FunctionalInterface
    public interface ApplyToTaskWithIndexFunction<Integer, Task> {
        Task apply(Integer t) throws TaskDoesNotExistException;
    }

    /**
     * Marks a task in the task list as not done.
     * @param index index of the task in the list
     * @return the marked task.
     * @throws TaskDoesNotExistException if the index given is invalid.
     */
    public Task markTaskAsUndone(int index) throws TaskDoesNotExistException {
        // check if task exists
        if (tasks.size() <= index) {
            throw new TaskDoesNotExistException();
        }
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Finds all tasks containing a specific keyword.
     * @param keyword the keyword
     * @return a TaskList containing all tasks containing the keyword.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> newListOfTasks = new ArrayList<>();
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.tasks.get(i);
            if (task.hasKeyword(keyword)) {
                newListOfTasks.add(task);
            }
        }
        return new TaskList(newListOfTasks);
    }

    /**
     * Returns the first index in the list which is out of bounds of tasks.
     * Checking this helps prevent IndexOutOfBounds
     * @param indexes to be checked
     * @return -1 if all indexes are valid.
     */
    public int findFirstInvalidIndex(int[] indexes) {
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] >= tasks.size()) {
                return indexes[i];
            }
        }
        return -1;
    }

    /**
     * Executes a function multiple times. This function must be a function which takes in indexes only.
     * @param f the function to execute multiple times
     * @param indexes the indexes of the tasks
     * @return list of tasks which had the function executed on.
     * @throws TaskDoesNotExistException if at least one of the indexes given are invalid.
     */
    public Task[] doMultiple(ApplyToTaskWithIndexFunction<Integer, Task> f, int[] indexes)
            throws TaskDoesNotExistException {
        // check for any invalid indexes
        int index = findFirstInvalidIndex(indexes);
        if (index != -1) {
            throw new TaskDoesNotExistException(index + 1);
        }
        // reverse indexes list because delete does not work in order
        int len = indexes.length;
        int[] reversedIndexes = new int[len];
        for (int i = 0; i < len; i++) {
            reversedIndexes[len - 1 - i] = indexes[i];
        }
        // initialise list of tasks to return later for ui to show
        ArrayList<Task> doneTasks = new ArrayList<>();
        // apply function to each item in the list
        for (int i: reversedIndexes) {
            Task doneTask = f.apply(i);
            doneTasks.add(doneTask);
        }
        // reverse the list back and convert back to array
        Task[] t = new Task[doneTasks.size()];
        for (int j = 0; j < doneTasks.size(); j++) {
            t[doneTasks.size() - 1 - j] = doneTasks.get(j);
        }
        return t;
    }

    /**
     * Returns a string representation of the list of tasks.
     * @return a string representation of the list of tasks.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int number = i + 1;
            str += (number + "." + task);
            if (i != tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
