import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasklist;

    TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    void add(Task task) {
        System.out.println("added: " + task.toString());
        this.tasklist.add(task);
    }

    void listContent() {
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ": " + tasklist.get(i).toString());
        }
    }



}
