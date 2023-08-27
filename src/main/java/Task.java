import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {

    protected static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    protected static final DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");
    protected static final ArrayList<Task> taskList= new ArrayList<>(1);
    protected String title;
    protected Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Get completed status of task.
     * @return X if task is completed, a blank space otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Modify status of current task.
     * @param isCompleted New status of task.
     */
    public void changeStatus(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    /**
     * Modify status of task based on index.
     * @param index ArrayList index of task to be modified.
     * @param isCompleted New status of task.
     */
    public static void changeStatusIndex(int index, boolean isCompleted) {
        try {
            Task pendingTask = taskList.get(index);
            pendingTask.isDone = isCompleted;
            Duke.output("Task updated as requested:\n       " + pendingTask);
        } catch (IndexOutOfBoundsException e) {                // When index falls outside ArrayList.
            Duke.output("I can't modify a task that doesn't exist...\n     Try a number from the list");
        }
    }

    /**
     * Delete task from taskList.
     * @param index ArrayList index of task.
     */
    public static void deleteTask(int index) {
        try {
            Task delete = taskList.remove(index);
            Duke.taskOutput(delete, "removed");
        } catch (IndexOutOfBoundsException e) {                // When index falls outside ArrayList.
            Duke.output("I can't remove a task that doesn't exist...\n     Try a number from the list");
        }
    }

    /**
     * Converts a task to a formatted string.
     * @return Status and title of task in string.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), title);
    }

    /**
     * Converts ArrayList of tasks to a string as a numbered list for chatbot output.
     * @return List of all tasks, formatted as numbered list.
     */
    public static String listToString() {
        StringBuilder allTasks = new StringBuilder("Here are your tasks:");
        int taskNo = taskList.size();
        for (int i = 0; i < taskNo; i++) {
            allTasks.append(String.format("\n     %d.%s", (i + 1), taskList.get(i).toString()));
        }
        return allTasks.toString();
    }

    /**
     * Returns a base string to be used for storing in data file.
     * @return Formatted string, to be further modified by overriding methods in child classes.
     */
    public String toFileString() {
        return String.format(" | %s | %s", this.getStatus(), title);
    }
}
