import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public void mark(int idx, boolean status) {
        this.taskList.get(idx - 1).mark(status);
    }

    public String showTask(int idx) {
        return this.taskList.get(idx - 1).toString();
    }

    public Task deleteTask(int taskIdx) {
        return this.taskList.remove(taskIdx - 1);
    }

    public void showList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            int idx = i + 1;
            System.out.println("     " + idx + ". " + showTask(idx));
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public int size() {
        return this.taskList.size();
    }
}
