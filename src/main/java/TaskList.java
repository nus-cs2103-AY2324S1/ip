import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        list = new ArrayList<>();
    }
    public void add(String item) {
        list.add(new Task(item));
        new AddedMessage(item).send();
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
