package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class containing the tasks created and saved. Also contains all operations to said tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage = new Storage(); //take tasks from Duke.Storage.

    /**
     * Constructor that tries to retrieve any previously stored tasks.
     * Returns empty ArrayList when there is nothing in file.
     */
    public TaskList() {
        try {
            this.tasks = this.storage.readTasks(); //will return empty ArrayList when nothing in file.
        } catch (FileNotFoundException e) {
            System.out.println("Looks like you do not have any previous tasks saved!");
        }
    }

    /**
     * Adds a Task into the ArrayList of Tasks.
     *
     * @param task specific Task to be added into the ArrayList.
     * @return a message showing the task has been added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.addedTask(task);
    }

    /**
     * Deletes specified Task from the ArrayList of Tasks.
     *
     * @param index specified index of Task to delete in the ArrayList.
     * @throws GmanException When there is no Task to delete.
     */
    public String deleteTask(int index) {
        if (index > this.getSize() || this.getSize() == 0) {
            return Ui.showError(new GmanException("HEYHEYHEY! There's nothing to delete here!"));
        } else {
            String removedTask = Ui.removedTask(tasks.get(index));
            tasks.remove(index);
            return removedTask;
        }

    }

    /**
     * Unmarks Task in specified index of tasks.
     *
     * @param index specified index of Task in tasks.
     * @return a string stating the task has been unmarked.
     */
    public String unmark(int index) {
        //needed to let tasks remain private
        Task task = tasks.get(index);
        task.unmark();
        return Ui.unmark(task.toString()); //i dont like how this is here and not in parser.
    }

    /**
     * Marks Task in specified index of tasks.
     *
     * @param index specified index of Task in tasks.
     * @return a string stating the task has been marked.
     */
    public String mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        return Ui.mark(task.toString());
    }

    /**
     * Returns the number of elements in the tasks ArrayList.
     *
     * @return int number of Elements in dynamic ArrayList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task from tasks given its index.
     *
     * @param index Index of specified Task to retrieve.
     * @return Task at index in ArrayList tasks.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Writes the toString format of tasks to the specified .txt file.
     *
     * @throws IOException
     */
    public void write() throws IOException {
        Storage.writeTasks(tasks);
    }


    /**
     * Finds all tasks in taskList with given keyword and puts these tasks in a new ArrayList.
     *
     * @param keyword The keyword to check if present in the task description.
     * @return a string of all tasks found with the specific keyword given.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                tasksWithKeyword.add(task);
            }
        }
        return Ui.listTasksFound(tasksWithKeyword);
    }

}
