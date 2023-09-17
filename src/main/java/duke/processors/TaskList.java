package duke.processors;

import duke.exception.DukeException;
import duke.task.Task;
import java.util.ArrayList;

/**
 * This class is used to store Task class in a ArrayList and perform
 * operations on the list.
 */
public class TaskList {

    private final ArrayList<Task> TASKS = new ArrayList<>();
    private int count;

    /**
     * A constructor for TaskList class.
     *
     * @param duke FileHandler to read data from txt file.
     */
    public TaskList(FileHandler duke) {
        duke.fileCreate();
        duke.readFile(TASKS);
        count = TASKS.size();
    }

    /**
     * Return the Task at the index given.
     *
     * @param ind index.
     * @return Task.
     */
    public Task get(int ind) throws DukeException {
        return this.TASKS.get(ind);
    }

    /**
     * Add a task to the ArrayList.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.TASKS.add(task);
        this.count++;
    }

    /**
     * List all the task in the ArrayList.
     */
    public String listTasks() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < count; i++) {
            tasks.append((i + 1)).append(". ").append(this.TASKS.get(i)).append("\n");
        }
        return tasks.toString();
    }

    /**
     * Delete the task at the given index.
     *
     * @param index the index of which task to be deleted.
     * @param duke to update the txt file.
     */
    public String deleteTask(int index, FileHandler duke) {
        Task delete = this.TASKS.remove(index);
        this.count--;

        duke.deleteLine(delete.toString());
        return "Noted. I've removed this task: \n" + "    " + delete.toString()
                + "\n" + "Now you have " + this.count + " TASKS in the list.";
    }

    /**
     * Output a list of tasks matching the keyword.
     *
     * @param keyword the word to be searched.
     */
    public String findTasks(String keyword) {
        int count = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your lists: \n");
        for (Task task : TASKS) {
            if (task.getDescription().contains(keyword)) {
                output.append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        return output.toString();
    }

    /**
     * Return the number of tasks in the list.
     *
     * @return integer.
     */
    public int getCount() {
        return this.count;
    }
}
