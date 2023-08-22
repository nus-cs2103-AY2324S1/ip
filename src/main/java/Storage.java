import java.util.ArrayList;

public class Storage {
    private ArrayList<String> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void formatPrintMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public void addTask(String task) {
        this.tasks.add(task);
        formatPrintMessage("added: " + task);
    }

    public void showAllTasks() {
        if (this.tasks.size() == 0) {
            formatPrintMessage("You have no tasks in your list.");
            return;
        }

        System.out.println();

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }

        System.out.println();
    }
}
