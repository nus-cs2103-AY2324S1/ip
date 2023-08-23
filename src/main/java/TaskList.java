import java.util.ArrayList;
public class TaskList {
    private ArrayList<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<String>();
    }

    public void addToList(String task) {
        this.tasks.add(task);
    }
    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            int x = i + 1;
            System.out.println(x + ". " + this.tasks.get(i));
        }
    }
}
