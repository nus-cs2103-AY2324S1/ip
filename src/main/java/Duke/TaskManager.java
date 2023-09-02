package Duke;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * TaskManager is the class deals with editing the taskList.
 */
public class TaskManager {

    private ArrayList<Task> tasklist;

    /**
     * Constructor for TaskManager.
     *
     * @param tasklist the tasklist
     */
    public TaskManager(ArrayList<Task> tasklist) {

        this.tasklist = tasklist;
    }

    /**
     * Display list array list.
     *
     * @return the array list
     */
    public ArrayList<Task> displayList() {
        return tasklist;
    }

    /**
     * mark method will mark the task mentioned as done.
     *
     * @param index the index
     */
    public void mark(int index) {
        tasklist.get(index).markAsDone();
    }

    /**
     * unmark method will unmark the task mentioned.
     *
     * @param index the index
     */
    public void unmark(int index) {
        tasklist.get(index).unmark();
    }

    /**
     * delete method will delete the mentioned task from the taskList.
     *
     * @param index the index
     */
    public void delete(int index) {
        tasklist.remove(index);
    }

    /**
     * todo method will create a new Todo task.
     *
     * @param description the description
     */
    public void todo(String description) {
        tasklist.add(new Todo(description));
    }

    /**
     * deadline method will create a new Deadline task.
     *
     * @param description the description
     * @param by          the by
     */
    public void deadline(String description, LocalDateTime by) {
        tasklist.add(new Deadline(description, by));
    }

    /**
     * event method will create a new Event task.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public void event(String description, LocalDateTime from, LocalTime to) {
        tasklist.add(new Event(description, from, to));
    }
}
