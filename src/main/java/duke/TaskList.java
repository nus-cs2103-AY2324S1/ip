package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = null;
    }
    public ArrayList<Task> task() {
        return tasks;
    }

    public int num() {
        return tasks.size();
    }


    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        System.out.println("List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public void mark(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                item.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(item.toString());
            } else {
                System.out.println("You have chosen an invalid task");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
        }
    }

    public void unmark(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                item.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(item.toString());
            } else {
                System.out.println("You have chosen an invalid task");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
        }
    }

    public int delete(String input, int number) {
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskIndex >= 0 && taskIndex < number) {
                Task item = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                number--;
                System.out.println("Noted. I've removed this task:");
                System.out.println(item.toString());
                System.out.println("Now you have " + number + " tasks in the list");
                return number;
            } else {
                System.out.println("You have chosen an invalid task");
                return number;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid task number.");
            return number;
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }
    // Implement methods to add/delete tasks, mark tasks as done, etc.
}
