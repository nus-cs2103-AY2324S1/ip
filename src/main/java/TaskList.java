import java.util.ArrayList;
import java.util.List;

class TaskList {
    private List<Task> list;
    public String divider = "____________________________________________________________";
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task task) {
        list.add(task);
        System.out.println(divider);
        System.out.println("added: " + task.toString());
        System.out.println(divider);
    }

    public void mark(int idx) {
        Task task = this.list.get(idx - 1);
        task.toggleIsDone(true);

        System.out.println(divider);
        System.out.println("Noice! I've marked this task as donezo:");
        System.out.println(task.toString());
        System.out.println(divider);
    }

    public void unmark(int idx) {
        Task task = this.list.get(idx - 1);
        task.toggleIsDone(false);

        System.out.println(divider);
        System.out.println("OK, I've marked this task as not done yet bruh:");
        System.out.println(task.toString());
        System.out.println(divider);
    }

    public void print() {
        System.out.println(divider);
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i+1) + task.toString());
        }
        System.out.println(divider);
    }
}