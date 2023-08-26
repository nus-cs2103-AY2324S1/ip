import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> tasks;
    private final String saveAddress;
    //static private FileWriter fw;

    TaskList(String saveAddress) {
        this.tasks = new ArrayList<>();
        this.saveAddress = saveAddress;
    }

    TaskList(ArrayList<Task> tasks, String saveAddress) {
        this.tasks = tasks;
        this.saveAddress = saveAddress;
    }

    ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private TaskList update(ArrayList<Task> tasks) {
        return new TaskList(tasks, this.saveAddress);
    }

    void add(Task task) {
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
    }

    TaskList delete(int index) throws IOException {
        System.out.println(" Noted. I've removed this task:\n");
        ArrayList<Task> currentTasks = this.tasks;
        System.out.println("    " + currentTasks.remove(index - 1) + "\n");
        System.out.println(("Now you have " + Integer.toString(currentTasks.size()) +
                " tasks in the list.\n"));
        TaskList newTasks = update(currentTasks);
        FileWriter fw = new FileWriter(saveAddress);
        fw.write(newTasks.toString());
        fw.close();
        return newTasks;
    }

    TaskList mark(int index) throws IOException {
        System.out.println("Nice! I've marked this task as done:\n");
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.mark());
        System.out.println("    " + currentTasks.get(index - 1));
        TaskList newTasks = update(currentTasks);
        FileWriter fw = new FileWriter(saveAddress);
        fw.write(newTasks.toString());
        fw.close();
        return newTasks;
    }

    TaskList unmark(int index) throws IOException {
        System.out.println("OK, I've marked this task as not done yet:\n");
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.unmark());
        System.out.println("    " + currentTasks.get(index - 1));
        TaskList newTasks = update(currentTasks);
        FileWriter fw = new FileWriter(saveAddress);
        fw.write(newTasks.toString());
        fw.close();
        return newTasks;
    }

    String dateSearch(LocalDate date) {
        String res= "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).happenOnThatDate(date)) {
                res = res + Integer.toString(i + 1) +
                        ": " + tasks.get(i).toString() + "\n";
            }
        }
        return res;
    }

    void save() throws IOException{
        FileWriter fw = new FileWriter(saveAddress);
        fw.write(this.toString());
        fw.close();
    }

    String saveMessage() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            result = result + Integer.toString(i + 1) +
                    ": " + tasks.get(i).toString() + "\n";
        }
        return result;
    }
}
