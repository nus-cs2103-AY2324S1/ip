package main.java.tasks;

import main.java.JukeASCIIColours;
import main.java.JukeObject;
import main.java.actions.JukeAction;
import main.java.actions.JukeErrorAction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * A manager of JukeTasks. This class handles the addition/deletion/manipulation of
 * other child JukeTasks subsumed under its control.
 */
public class JukeTaskManager extends JukeObject {
    /** List of JukeTasks under this Task Manager's control. */
    private final LinkedList<JukeTask> tasks;

    /**
     * Private constructor for JukeTaskManager.
     */
    private JukeTaskManager() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Private constructor for JukeTaskManager that initialises with a series of JukeTasks.
     * @param initialTasks List of initial JukeTasks
     */
    private JukeTaskManager(List<? extends JukeTask> initialTasks) {
        this.tasks = new LinkedList<>(initialTasks);
    }

    /**
     * Factory method to create a JukeTaskManager.
     * @return JukeTaskManager object
     */
    public static JukeTaskManager of() {
        return new JukeTaskManager();
    }

    /**
     * Factory method to create a JukeTaskManager with initial tasks.
     * @param initialTasks Initial tasks.
     * @return JukeTaskManager object
     */
    public static JukeTaskManager ofExisting(List<? extends JukeTask> initialTasks) {
        return new JukeTaskManager(initialTasks);
    }

    /**
     * Adds a task.
     * @param task JukeTask object.
     * @return true if the task is added, else false
     */
    public boolean addTask(JukeTask task) {
        return this.tasks.add(task);
    }

    /**
     * Marks a task as complete.
     * @param index Index of task to act on.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public Optional<? extends JukeAction> markAsDone(int index) {
        if (index < 0 || index > this.tasks.size()) {
            return Optional.of(new JukeErrorAction("Oh no! I do not have such task recorded!"));
        }

        return this.tasks.get(index).markAsComplete();
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to act on.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public Optional<? extends JukeAction> markAsUndone(int index) {
        if (index < 0 || index > this.tasks.size()) {
            return Optional.of(new JukeErrorAction("Oh no! I do not have such task recorded!"));
        }

        return this.tasks.get(index).markAsIncomplete();
    }

    /**
     * Returns information of the task at the specified index.
     * @param index Index of task to act on.
     * @return String representation of the task.
     */
    public String taskInformation(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * String representation of the task manager. This consists of all the tasks
     * managed by this task manager.
     * @return String representation of the task manager.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\t>>>>>>>>>>>>>>>>>>>> TASK LIST <<<<<<<<<<<<<<<<<<<<\n");

        if (this.tasks.isEmpty()) {
            builder.append(JukeASCIIColours.RED + "\t\t\t\t\t!No Tasks Present!" + JukeASCIIColours.RESET);
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append("\t")
                   .append((i + 1) + ". ")
                   .append(this.tasks.get(i))
                   .append("\n");
        }

        return builder.toString();
    }

}
