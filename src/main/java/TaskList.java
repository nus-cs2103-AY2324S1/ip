import java.util.LinkedList;

public class TaskList {
    protected LinkedList<Task> storage;

    /**
     * Constructor to build a Task Manager.
     */
    public TaskList() {
        this.storage = new LinkedList<Task>();
    }

    public void addTask(Task task) {
        this.storage.add(task);
    }

    public Task getTask(int taskIndex) {
        return this.storage.get(taskIndex);
    }

    public void deleteTask(int taskIndex) {
        this.storage.remove(taskIndex);
    }

    public int getSize() {
        return this.storage.size();
    }
    public String outputNumberedList() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.storage.size(); i++) {
            Task currTask = this.storage.get(i);
            str.append(i + 1).append(".")
                    .append(currTask.toString())
                    .append("\n");
        }
        return str.toString();
    }
}
