import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList add(Task task) {
        System.out.println(" Got it. I've added this task:\n");
        ArrayList<Task> currentTasks = this.tasks;
        currentTasks.add(task);
        System.out.println("    " + task + "\n");
        System.out.println(("Now you have " + Integer.toString(currentTasks.size()) +
                " tasks in the list.\n"));
        return new TaskList(currentTasks);
    }

    TaskList mark(int index) {
        System.out.println("Nice! I've marked this task as done:\n");
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.mark());
        System.out.println("    " + currentTasks.get(index - 1));
        return new TaskList(currentTasks);
    }

    TaskList unmark(int index) {
        System.out.println("OK, I've marked this task as not done yet:\n");
        ArrayList<Task> currentTasks = this.tasks;
        Task target = currentTasks.get(index - 1);
        currentTasks.set(index - 1, target.unmark());
        System.out.println("    " + currentTasks.get(index - 1));
        return new TaskList(currentTasks);
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
