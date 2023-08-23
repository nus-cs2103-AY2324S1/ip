import java.util.ArrayList;
public class Storage {
    private static final ArrayList<Task> list = new ArrayList<>();

    public static void addToTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public static void mark(int index) {
        Task taskToEdit = list.get(index - 1);
        taskToEdit.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskToEdit);
    }

    public static void unMark(int index) {
        Task taskToEdit = list.get(index - 1);
        taskToEdit.unMark();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskToEdit);
    }
}
