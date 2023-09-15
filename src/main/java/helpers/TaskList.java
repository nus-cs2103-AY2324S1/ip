package helpers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tasks.Task;

/**
 * Represents TaskList class that contains the list of tasks as well as CRUD operations
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Empty constructor for no tasks input
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor with tasks in input
     *
     * @param tasks List of tasks to initialise
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method to add a task into the list
     *
     * @param task Task object, can be deadline, to-do or events
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Method to remove a task from the list
     *
     * @param index Element to remove the task
     * @return Task that was removed
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Invalid index";
        return tasks.remove(index);
    }

    /**
     * Method to mark task as done
     *
     * @param index Element to mark the task
     * @return Task that was modified
     */
    public Task markTaskAsDone(int index) {
        Task t = tasks.get(index);
        t.markedAsDone();
        return t;
    }

    /**
     * Method to mark task as undone
     *
     * @param index Element to unMark the task
     * @return Task that was modified
     */
    public Task markTaskAsUnDone(int index) {
        Task t = tasks.get(index);
        t.markedAsUndone();
        return t;
    }

    /**
     * Method to get the number of tasks in the list
     *
     * @return Number of tasks
     */
    public int getListLength() {
        return tasks.size();
    }

    /**
     * Method to get the list of tasks
     *
     * @return Tasklist
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Method to print the task list
     *
     * @return String representation on the list of tasks
     */
    public String printTaskList() {
        if (this.tasks.isEmpty()) {
            return "No tasks recorded, macho!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the list of tasks recorded: \n");
        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            Task task = this.tasks.get(i);
            sb.append(index).append(".").append(task.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Method to filter the task list given user's string input
     * @param regex Pattern to match in the task list
     * @return Filtered task list
     */
    public String filterTaskList(String regex) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        for (Task t : this.tasks) {
            int index = this.tasks.indexOf(t) + 1;
            Matcher matcher = pattern.matcher(t.toString());

            if (matcher.find()) {
                sb.append(index).append(". ").append(t);

                if (index < this.tasks.size()) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Method to sort task by task desc
     * @return String representation of the sorted tasks
     */
    public String sortTaskList(boolean isAsc) {
        StringBuilder sb = new StringBuilder();
        if (isAsc) {
            this.tasks.sort(Comparator.comparing(Task::getTaskDesc));
        } else {
            this.tasks.sort(Comparator.comparing(Task::getTaskDesc).reversed());
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            int index = i + 1;
            Task task = this.tasks.get(i);
            sb.append(index).append(".").append(task.toString()).append("\n");
        }
        return sb.toString();
    }

}
