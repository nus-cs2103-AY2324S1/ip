import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>(1);
    }

    /**
     * Modify status of task based on index.
     * @param index ArrayList index of task to be modified.
     * @param isCompleted New status of task.
     */
    public void changeStatusIndex(int index, boolean isCompleted) {
        try {
            Task pendingTask = taskList.get(index);
            pendingTask.isDone = isCompleted;
            Ui.output("Task updated as requested:\n       " + pendingTask);
        } catch (IndexOutOfBoundsException e) {                // When index falls outside ArrayList.
            Ui.output("I can't modify a task that doesn't exist...\n     Try a number from the list");
        }
    }

    /**
     * Add task to taskList.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Ui.taskOutput(task, "added", taskList.size());
    }

    /**
     * Delete task from taskList.
     * @param index ArrayList index of task.
     */
    public void deleteTask(int index) {
        try {
            Task delete = taskList.remove(index);
            Ui.taskOutput(delete, "removed", taskList.size());
        } catch (IndexOutOfBoundsException e) {                // When index falls outside ArrayList.
            Ui.output("I can't remove a task that doesn't exist...\n     Try a number from the list");
        }
    }

    /**
     * Converts ArrayList of tasks to a formatted string suited for storing in data file.
     * @return Formatted string of tasks to store in data file.
     */
    public String listToStringData() {
        StringBuilder fileData = new StringBuilder();
        for (Task task : taskList) {
            String taskString = task.toFileString() + "\n";
            fileData.append(taskString);
        }
        return fileData.toString();
    }

    /**
     * Converts ArrayList of tasks to a string as a numbered list for chatbot output.
     * @return List of all tasks, formatted as numbered list.
     */
    public String listToString() {
        StringBuilder allTasks = new StringBuilder("Here are your tasks:");
        int taskNo = taskList.size();
        for (int i = 0; i < taskNo; i++) {
            allTasks.append(String.format("\n     %d.%s", (i + 1), taskList.get(i).toString()));
        }
        return allTasks.toString();
    }
}
