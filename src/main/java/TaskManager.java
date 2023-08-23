import exceptions.InvalidArgumentException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list = new ArrayList<>();
    private int index = 0;
    private int numOfTasks = 0;

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void add (Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        printLine();
        System.out.println("Got it. I've added the task:\n" + task);
        if (numOfTasks != 1) {
            System.out.println("Now you have " + numOfTasks + " tasks in your list, just like how I have 5 Ballon d'Ors.");
        } else {
            System.out.println("Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.");
        }
        printLine();
    }

    public void list() {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        printLine();
    }

    public void mark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + "tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.mark();
    }

    public void unmark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.unmark();
    }

    public void delete(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " tasks.");
        }
        numOfTasks -= 1;
        Task removedTask = list.get(index);
        list.remove(index);
        printLine();
        System.out.println("Removed task:\n" + removedTask);
        printLine();
    }


}
