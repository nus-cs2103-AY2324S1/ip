package duke.tasks;

import duke.tasks.Task;
import java.util.ArrayList;

/**
 * Encapsulates a task list to be managed by the chatbot.
 * Utilizes an ArrayList to store the Tasks.
 *
 * @author Wu Jingya
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the string representation of the Task at the specified index in the TaskList.
     *
     * @param index The index of the Task in the TaskList.
     * @return The string representation of the Task.
     */
    public String getTaskAsString(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Prints the entire TaskList to console as a numbered list.
     */
    public void printTasksAsList() {
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }

    /**
     * Adds the specified Task to the TaskList.
     *
     * @param newTask The Task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes the Task at the specified index from the TaskList.
     *
     * @param index The index of the Task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the Task at the specified index in the TaskList.
     *
     * @param index The index of the Task.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of Tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Marks or unmarks the Task at the specified index as done.
     * Marks the Task as done if the argument done is true, and unmarks the Task as done if otherwise.
     *
     * @param index The index of the Task.
     * @param done Whether the Task has been completed.
     */
    public void markTaskAsDone(int index, boolean done) {
        getTask(index).markTaskCompleted(done);
    }

    /**
     * Returns the string representation of the data of all the Tasks in the TaskList.
     * This string contains all the data that is written to hard drive when the TaskList
     * is saved.
     *
     * @return The string representation of the TaskList data.
     */
    public String getTaskListData() {
        String data = "";
        for (Task task : tasks) {
            data = data.concat(task.toData() + "\n");
        }
        return data;
    }
}
