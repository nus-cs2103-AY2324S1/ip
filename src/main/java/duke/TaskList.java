package duke;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;
    private int numOfCompletedTasks = 0;
    public enum TaskType {
        TASK, TODO, DEADLINE, EVENT
    }

    /**
     * Creates a new {@code ArrayList} to store the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a {@code Task} to the {@code ArrayList}.
     *
     * @param t {@code Task} to be added.
     */
    public void add(Task t) {
        tasks.add(t);
        this.numOfTasks++;
        if (t.isCompleted) {
            this.numOfCompletedTasks++;
        }
    }

    /**
     * Gets the {@code Task} object at the specified index.
     *
     * @param i Index of the {@code Task}.
     * @return {@code Task} object at index i.
     */
    public Task get(int i) {
        if (i > -1 && i < numOfTasks) {
            return tasks.get(i);
        } else {
            return null;
        }
    }

    /**
     * Gets the total number of {@code Task}s in the {@code ArrayList}.
     *
     * @return Total number of {@code Task}s.
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    /**
     * Gets the total number of completed {@code Task}s in the {@code ArrayList}.
     *
     * @return Total number of completed {@code Task}s.
     */
    public int getNumOfCompletedTasks() {
        return this.numOfCompletedTasks;
    }

    /**
     * Checks if the {@code ArrayList} is empty.
     *
     * @return {@code true} iff the {@code ArrayList} is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.numOfTasks == 0;
    }

    /**
     * Checks if there are completed tasks in the {@code ArrayList}.
     *
     * @return {@code true} iff the {@code ArrayList} contains at least one completed task;
     * {@code false} otherwise.
     */
    public boolean hasCompletedTasks() {
        return this.numOfCompletedTasks > 0;
    }

    /**
     * Increments the number of completed tasks when a {@code Task} is marked as complete.
     */
    public void incrementCompletedTasks() {
        this.numOfCompletedTasks++;
    }

    /**
     * Decrements the number of completed tasks when a {@code Task} is marked as incomplete.
     */
    public void decrementCompletedTasks() {
        this.numOfCompletedTasks--;
    }

    /**
     * Checks if there already exists a {@code Task} in the {@code ArrayList} with the
     * given details.
     *
     * @param details Details to be checked.
     * @return {@code true} iff there exists a {@code Task} with the given details;
     * false otherwise.
     */
    public boolean checkDuplicates(String details) {
        for (Task t : tasks) {
            if (details.equals(t.getDetails())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the {@code TaskType} of the {@code Task} at the specified index.
     *
     * @param i Index of the {@code Task}.
     * @return {@code TaskType} of {@code Task} at index i.
     */
    public TaskType getTaskType(int i) {
        Task t = this.get(i);
        if (t instanceof ToDo) {
            return TaskType.TODO;
        } else if (t instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if (t instanceof Event) {
            return TaskType.EVENT;
        } else {
            return TaskType.TASK;
        }
    }

    /**
     * Removes a {@code Task} from the {@code ArrayList}.
     *
     * @param t {@code Task} to be removed.
     */
    public void remove(Task t) {
        tasks.remove(t);
        this.numOfTasks--;
        if (t.isCompleted) {
            this.numOfCompletedTasks--;
        }
    }
}
