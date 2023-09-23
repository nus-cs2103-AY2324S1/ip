package duke;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * TaskManager is the class deals with editing the tasks.
 */
public class TaskManager {

    private final ArrayList<Task> tasks;

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
    public void deadline(String description, String by) throws DukeException {
        tasks.add(new Deadline(description, by));
    }

    /**
     * event method will create a new Event task.
     *
     * @param description the description
     * @param from        the from date and time
     * @param to          the to time
     */
    public void event(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * find method will find the matching tasks.
     *
     * @param description the description
     * @return the array list
     */
    public ArrayList<Task> find(String description) {
        ArrayList<Task> tasksFound = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getStatusIcon().contains(description)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    /**
     * Update.
     *
     * @param index       the index
     * @param description the description
     */
    public void update(int index, String description) {
        String[] taskType = description.split(" ", 2);
        switch (taskType[0]) {
        case "todo":
            tasks.set(index, new Todo(taskType[1]));
            break;
        case "deadline":
            String[] fullDesc = taskType[1].split(" /by ");
            tasks.set(index, new Deadline(fullDesc[0], fullDesc[1]));
            break;
        case "event":
            String[] fullDes = taskType[1].split(" /from | /to ");
            tasks.set(index, new Event(fullDes[0], fullDes[1], fullDes[2]));
            break;
        default:
            break;
        }
    }
}
