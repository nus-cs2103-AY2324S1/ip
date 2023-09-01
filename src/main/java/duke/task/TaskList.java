package duke.task;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Parser;
import duke.core.Storage;

/**
 * Class to store and handle tasks.
 */
public class TaskList {
    private boolean hasLoadingError = false;
    private ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {

    }

    /**
     * Constructor for TaskList with tasks.
     * 
     * @param taskDataStream Stream of task data strings.
     */
    public TaskList(Stream<String> taskDataStream) {
        taskDataStream.forEach(taskData -> {
            try {
                Parser.parseTaskDataString(taskData).execute(this, null, null);
            } catch (DukeException e) {
                this.hasLoadingError = true;
            }
        });
    }

    public boolean hasLoadingError() {
        return this.hasLoadingError;
    }
    
    /**
     * Returns the number of tasks in the task list.
     * 
     * @return Number of tasks in the task list.
     */
    public int size() {
        return this.taskArray.size();
    }

    /**
     * Returns a stream of tasks in the task list.
     * 
     * @return Stream of tasks in the task list.
     */
    public Stream<Task> getTasks() {
        return this.taskArray.stream();
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    /**
     * Marks a task as done.
     * 
     * @param taskIndex Index of task to be marked as done.
     * @return Task that was marked as done.
     * @throws DukeException If index provided is out of bounds.
     */
    public Task markAsDone(int taskIndex) throws DukeException {
        try {
            return taskArray.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    /**
     * Marks a task as not done.
     * 
     * @param taskIndex Index of task to be marked as not done.
     * @return Task that was marked as not done.
     * @throws DukeException If index provided is out of bounds.
     */
    public Task markAsUndone(int taskIndex) throws DukeException {
        try {
            return taskArray.get(taskIndex).markAsUndone();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    /**
     * Deletes a task from the task list.
     * 
     * @param taskIndex Index of task to be deleted.
     * @return String representation of task that was deleted.
     * @throws DukeException If index provided is out of bounds.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        try {
            return taskArray.remove(taskIndex).toString();
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("Task number provided does not exist.");
        }
    }

    /**
     * Stores tasks in the task list to a file.
     * 
     * @throws DukeException If there is an error writing to the file.
     */
    public void storeTasks() throws DukeException {
        Storage.writeFile("tasks.txt", taskArray.stream().map(task -> task.getDataString()));
    }
}