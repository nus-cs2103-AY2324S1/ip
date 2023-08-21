package main.java;

import main.java.exceptions.JukeException;
import main.java.primitivies.JukeObject;
import main.java.actions.JukeAction;
import main.java.tasks.JukeTask;

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
     * Adds a task.
     * @param task JukeTask object.
     * @return true if the task is added, else false
     */
    public boolean addTask(JukeTask task) {
        return this.tasks.add(task);
    }

    /**
     * Deletes a task.
     * @param task JukeTask object
     * @return true if the task is successfully deleted, else false
     */
    public boolean deleteTask(JukeTask task) {
        return this.tasks.remove(task);
    }

    /**
     * Deletes a task by index.
     * @param task Index of JukeTask object
     * @return true if the task is successfuly deleted, else false
     */
    public JukeTask deleteTask(int task) throws JukeException{
        try {
            JukeTask retTask = this.tasks.get(task);
            this.tasks.remove(retTask);
            return retTask;
        } catch (IndexOutOfBoundsException ex) {
            throw new JukeException("Oh no! The task index you have provided is not valid!");
        }
    }

    /**
     * Marks a task as complete.
     * @param index Index of task to act on.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public void markAsDone(int index) throws JukeException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsComplete();
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to act on.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public void markAsUndone(int index) throws JukeException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsIncomplete();
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
            builder.append("\t\t\t\t\t!No Tasks Present!");
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
