package ben;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task, Boolean isDisplayMessage) {
        tasks.add(task);
        if (isDisplayMessage) {
            addSuccessMessage(task);
        }
    }
    public void addSuccessMessage(Task task) {
        Ui.displayMessage("\nGot It! This task has been added:\n" + task +
                "\nNow you have " + tasks.size() + " items in the list\n");
    }

    public void delete(Task task) {
        tasks.remove(task);
        Ui.displayMessage("\n" + "Sure thing! This task has been removed\n" + task +
                "\nNow you have " + tasks.size() + " tasks left\n");
    }

    public String saveTasks() {
        StringBuilder s = new StringBuilder();
        for (Task task : tasks) {
            s.append(task.saveString()).append("\n");
        }
        return s.toString();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        String message = "";
        for (int i = 1; i <= tasks.size(); i++) {
            message += i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return message;
    }
}
