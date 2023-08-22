import java.util.ArrayList;

public class TaskList {

    // ArrayList of tasks to keep track of all tasks
    private ArrayList<Task> taskList;

    // Counter to keep track of position

    public TaskList() {
        taskList = new ArrayList<>();
    }

    // Adds an item to the list while notifying the user
    public void add(String text) {
        taskList.add(new Task(text));
        System.out.println("____________________________________________________________\n" +
                "added: " + text + "\n" +
                "____________________________________________________________");
    }

    // Displays all items to the user
    public void display() {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int plusOne = i + 1; // Increment by one so starting display index is 1
            System.out.println(plusOne + ". [" + taskList.get(i).getStatusIcon() + "] " + taskList.get(i).getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    // Marks the item
    public void mark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.mark();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done: \n" +
                "[" + curr.getStatusIcon() + "] " + curr.getDescription() + "\n" +
                "____________________________________________________________");

    }

    // Unmarks the item
    public void unmark(int index) {
        Task curr = taskList.get(index - 1); // Decrement by 1 to match display index
        curr.unmark();
        System.out.println("____________________________________________________________\n" +
                "Cool! I've marked this task as not done yet: \n" +
                "[" + curr.getStatusIcon() + "] " + curr.getDescription() + "\n" +
                "____________________________________________________________");

    }
}
