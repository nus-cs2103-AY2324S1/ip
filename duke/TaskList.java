package duke;
import java.util.ArrayList;
import java.io.Serializable;

public class TaskList implements Serializable {
    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    public void listTasks() {
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public String deleteTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.remove(id - 1);
            return "Noted. I've removed this task:\n" + task.toString()
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    public String addTask(Task task) {
        taskArrayList.add(task);
        return "Got it. I've added this task:\n" + taskArrayList.get(taskArrayList.size() - 1).toString()
                    + "\nNow you have " + taskArrayList.size() + " tasks in the list.";
    }

    public String markTaskAsDone(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (!task.isDone) {
                task.markAsDone();
                return "Nice! I've marked this task as done:\n" + task.toString();
            } else {
                return "This task is already marked as done:\n" + task.toString();
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    public String unmarkTask(int id) {
        if (id >= 1 && id <= taskArrayList.size()) {
            Task task = taskArrayList.get(id - 1);
            if (task.isDone) {
                task.markAsUndone();
                return "OK, I've marked this task as not done yet:\n" + task.toString();
            } else {
                return "This task is already marked as not done:\n" + task.toString();
            }
        } else {
            return "Invalid task number. Please enter a valid task number.";
        }
    }

    protected ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

}
