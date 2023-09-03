import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void printTaskList() {
        int index = 0;
        for (Task task: this.taskList) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }
}
