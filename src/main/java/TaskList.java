import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public void mark(int id, boolean isDone) {
        this.taskList.get(id - 1).mark(isDone);
    }

    public String showTask(int id) {
        return this.taskList.get(id - 1).toString();
    }

    public Task deleteTask(int taskId) {
        return this.taskList.remove(taskId - 1);
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            int id = i + 1;
            String temp = "     " + id + ". " + showTask(id);
            ans.append(temp);
            if (i < this.taskList.size() - 1) {
                ans.append('\n');
            }
        }
        return ans.toString();
    }
}
