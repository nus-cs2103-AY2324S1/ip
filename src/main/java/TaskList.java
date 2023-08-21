import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        taskCountSummary();
    }

    public void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index - 1));
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index - 1));
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            taskCountSummary();
        } else {
            throw new DukeException("☹ OOPS!!! Invalid task index.");
        }
    }

    public void taskCountSummary() {
        int size = tasks.size();
        if (size == 1) {
            System.out.println("Now you have " + size + " task in the list.");
        } else {
            System.out.println("Now you have " + size + " tasks in the list.");
        }
    }
}
