import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public void add(Task item) {
        this.list.add(item);
        new AddTaskMessage(item, this.list.size()).send();
    }
    public void delete(int num) {
        Task task = this.list.remove(num - 1);
        new RemoveTaskMessage(task, this.list.size()).send();
    }
    public void printList() {
        new TaskListMessage(list).send();
    }
    public void markTask(int num) {
        Task task = this.list.get(num - 1);
        new MarkTaskMessage(task).send();
    }
    public void unmarkTask(int num) {
        Task task = this.list.get(num - 1);
        new UnmarkTaskMessage(task).send();
    }
}
