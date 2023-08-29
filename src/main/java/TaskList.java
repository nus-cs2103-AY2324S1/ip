import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void deleteTask(int index) {
        this.list.remove(index);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void markTask(int index) {
        this.list.get(index).mark();
    }

    public void unmarkTask(int index) {
        this.list.get(index).unmark();
    }

    public void printList() {
        System.out.println(Duke.lineSeparator);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < this.list.size(); i++) {
            System.out.printf("%d. %s", i + 1, this.list.get(i).toString());
        }
    }

    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

}
