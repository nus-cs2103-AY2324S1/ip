import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> list;

    public ToDoList() {
        this.list = new ArrayList<>();
    }

    public void addTask(String task) {
        this.list.add(task);
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("added: " + task);
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    public void listTasks() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(i + 1 + ". " + this.list.get(i));
        }
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }
}
