package qi.tasklist;

import qi.task.Task;

import java.util.ArrayList;

/**
 * Stores the tasks in the form of a list.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Initializes the inner data structure to store tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Stores the task received into the list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks the task with given id as done or undone.
     *
     * @param id Id of the task
     * @param isDone Status for the completion of the task
     */
    public void mark(int id, boolean isDone) {
        this.taskList.get(id - 1).mark(isDone);
    }

    /**
     * Shows the description of the task with given id.
     *
     * @param id Id of the task
     * @return String representation of the task
     */
    public String showTask(int id) {
        return this.taskList.get(id - 1).toString();
    }

    public Task deleteTask(int taskId) {
        return this.taskList.remove(taskId - 1);
    }

    public int size() {
        return this.taskList.size();
    }

    public String matchingKeyWord(String keyWord) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            if (this.taskList.get(i).isMatching(keyWord)) {
                int id = i + 1;
                ans.append("     " + id + " " + this.showTask(id));
                ans.append('\n');
            }
        }

        return ans.toString();
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            int id = i + 1;
            String temp = "     " + id + ". " + showTask(id);
            ans.append(temp);
            ans.append('\n');
        }
        return ans.toString();
    }
}
