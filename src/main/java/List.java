import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;
    String line = "\n    _____________________________________________________________________________\n      ";

    public List() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        list.add(task);
        System.out.println(line + "added: " + task.getName() + line);
    }

    public void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printTask(i + 1);
        }
        System.out.println(line);
    }
}
