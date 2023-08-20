import java.util.ArrayList;

public class TaskList {

    private static final String DIVIDER = "____________________________________________________________";
    private ArrayList<String> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(String task) {
        System.out.println(DIVIDER);
        this.list.add(task);
        System.out.println("added: " + task);
        System.out.println(DIVIDER);
    }

    public void listTasks() {
        System.out.println(DIVIDER);
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i + 1) + ". " + this.list.get(i));
        }
        System.out.println(DIVIDER);
    }
}
