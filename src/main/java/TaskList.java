import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private String filePath = "./data/tasks.txt";

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskIndex) throws BuddyException {
        if (tasks.size() == 0 ) {
            System.out.println("There are no tasks in your list:");
        } else if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task deletedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask.toString());
            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    public void markAsNotDone(int taskIndex) throws BuddyException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).markTaskAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex).toString());
        } else {
            throw new BuddyException("Invalid task index.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i));
                sb.append("\n");
            }
        } else {
            sb.append("There are no tasks in your list.\n");
        }
        return sb.toString();
    }
}
