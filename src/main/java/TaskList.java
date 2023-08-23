import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> store;

    public TaskList() {
        store = new ArrayList<>();
    }

    public void addTask(String title) {
        System.out.println("added: " + title);
        store.add(new Task(title));
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for(Task task: store) {
            System.out.println(counter + "." + task);
            counter++;
        }
    }

    public void markTask(int index) {
        Task curr = store.get(index - 1);
        curr.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + curr);
    }

    public void unmarkTask(int index) {
        Task curr = store.get(index - 1);
        curr.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + curr);
    }
}
