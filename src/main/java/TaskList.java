import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a new TaskList with added task.
     * @param task Task to be added
     * @return TaskList with added task
     */
    public TaskList add(Task task) {
        ArrayList<Task> newTasks = this.tasks;
        newTasks.add(task);
        return new TaskList(newTasks);
    }

    /**
     * Returns the list of tasks to be done.
     * @return List of tasks to be done
     */
    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            outputString.append(String.format("%d. %s\n", i + 1, task.toString()));
        }
        if (this.tasks.isEmpty()) {
            outputString.append("You have no pending tasks.");
        }
        return outputString.toString();
    }
}
