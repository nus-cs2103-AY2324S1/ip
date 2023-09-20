package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.task.Task;

import javax.imageio.IIOException;


/**
 * A class to represent a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new task list with the given list of tasks.
     *
     * @param tasks The list of tasks to add.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public String addTask(Task t, Storage storage) throws DuplicateTaskException {
        StringBuilder sb = new StringBuilder();

        if (this.taskList.contains(t)) {
            throw new DuplicateTaskException();
        }
        this.taskList.add(t);
        sb.append("Got it. I've added this task:\n");
        sb.append(t + "\n");
        sb.append("Now you have " + this.taskList.size() + " in the list." + "\n");
        try{
            storage.writeTasksToFile(this.taskList);
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, error writing tasks to file");
        }
        return sb.toString();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     */
    public String deleteTask(int i, Storage storage) {
        Task t = this.taskList.get(i);
        this.taskList.remove(i);
        int length = this.taskList.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Noted! I've removed this task:" + "\n");
        sb.append(t + "\n");
        sb.append("Now you have " + length + " tasks in the list." + "\n");
        try{
            storage.writeTasksToFile(this.taskList);
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, error writing tasks to file");
        }
        return sb.toString();
    }

    /**
     * Prints the tasks in the list that contain the String s.
     *
     * @param s The string to search among the tasks
     */
    public String findMatching(String s) {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:" + "\n");
        for (Task t : this.taskList) {
            if (t.contains(s)) {
                sb.append(index + "." + t + "\n");
                index += 1;
            }
        }
        return sb.toString();
    }

    /**
     * Prints the tasks in the list.
     */
    public String printTasks() {
        int len = this.taskList.size();
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:" + "\n");
        for (int i = 0; i < len; i++) {
            int taskNumber = i + 1;
            Task t = this.taskList.get(i);
            sb.append(taskNumber
                    + "."
                    + t + "\n");
        }
        return sb.toString();
    }
}
