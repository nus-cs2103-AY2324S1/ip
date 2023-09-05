package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * TaskManager is the class deals with editing the tasks.
 */
public class TaskManager {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskManager.
     *
     * @param taskList the tasks
     */

    public TaskManager(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Display list array list.
     *
     * @return the array list
     */
    public ArrayList<Task> displayList() {
        return tasks;
    }

    /**
     * mark method will mark the task mentioned as done.
     *
     * @param index the index
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * unmark method will unmark the task mentioned.
     *
     * @param index the index
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * delete method will delete the mentioned task from the tasks.
     *
     * @param index the index
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * todo method will create a new Todo task.
     *
     * @param description the description
     */
    public void todo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * deadline method will create a new Deadline task.
     *
     * @param description the description
     * @param by          the by
     */
    public void deadline(String description, LocalDateTime by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * event method will create a new Event task.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public void event(String description, LocalDateTime from, LocalTime to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * find method will find the matching tasks.
     *
     * @param description the description
     * @return the array list
     */
    public ArrayList<Task> find(String description) {
        ArrayList<Task> tasksFound = new ArrayList<Task>();

        for (Task task: tasks) {
            if (task.getStatusIcon().contains(description)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }
}
