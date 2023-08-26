import java.util.ArrayList;

// Class to handle keeping track of tasks
public class TaskList {

    // ArrayList of tasks to keep track of all tasks
    private ArrayList<Task> taskList;

    // Counter to keep track of position

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    // Adds an item to the list while notifying the user
    public void add(Task task, boolean fromFile) {
        taskList.add(task);
        if (!fromFile) {
            System.out.println("____________________________________________________________\n" +
                    "Alright! I've added this task:\n " + " " + task
                    + "\nNow you have " + taskList.size() + " tasks in the list.\n" +
                    "____________________________________________________________");
        }
    }

    // Displays all items to the user
    public void display() {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int plusOne = i + 1; // Increment by one so starting display index is 1
            System.out.println(plusOne + ". " + taskList.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    // Marks the item
    public void mark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.mark();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" + "  " +
                curr +
                "\n____________________________________________________________");

    }

    // Unmarks the item
    public void unmark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.unmark();
        System.out.println("____________________________________________________________\n" +
                "Cool! I've marked this task as not done yet:\n" + "  " +
                curr +
                "\n____________________________________________________________");

    }

    public void delete(int index) {
        Task curr =taskList.get(index - 1);
        taskList.remove(index - 1);
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + "  " + curr + "\n Now You have " + this.size() +
                " tasks in the list.\n"  +
                "____________________________________________________________");
    }
}
