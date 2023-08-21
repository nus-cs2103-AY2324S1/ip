import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void setTaskComplete(int i) {
        this.list.get(i).setDone();
    }

    public void setTaskIncomplete(int i) {
        this.list.get(i).setNotDone();
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }
}
