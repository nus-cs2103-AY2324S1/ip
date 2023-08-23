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

    public void markTask(String input) {
        int index = input.charAt(input.length() - 1) - '0' - 1;
        Task task = list.get(index);
        System.out.println(line + "Nice! I've marked this task as done: \n");
        task.markDone();
        System.out.println(line);
    }

    public void unmarkTask(String input) {
        int index = input.charAt(input.length() - 1) - '0' - 1;
        Task task = list.get(index);
        System.out.println(line + "OK, I've marked this task as not done yet: \n");
        task.unmarkDone();
        System.out.println(line);
    }

    public void printList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printTask(i + 1);
        }
        System.out.println(line);
    }
}
