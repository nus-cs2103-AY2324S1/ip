import java.util.ArrayList;
import java.util.Iterator;
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Constructor to load existing tasks
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }

    // Additional methods for any other operations you want on your task list can be added here
    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNumber - 1);
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
