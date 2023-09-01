package duke.tasks;

import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.printMessageWithSeparator("Got it. I've added this task:\n" + task.getDescription()
                + "\nNow you have " + tasks.size() + " duke.tasks in the list.");
    }

    public void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            Ui.printMessageWithSeparator("Noted. I've removed this task:\n" + task.getDescription()
                    + "\nNow you have " + tasks.size() + " duke.tasks in the list.");
        }
    }

    public void markTaskAsDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.markAsDone();
            Ui.printMessageWithSeparator("Nice! I've marked this task as done:\n" + task.getDescription());
        }
    }

    public void unmarkTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            Ui.printMessageWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index);
            task.unmark();
            Ui.printMessageWithSeparator("OK, I've marked this task as not done yet:\n" + task.getDescription());
        }
    }

    public void list() {
        System.out.println(Ui.LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDescription());
        }
        System.out.println(Ui.LINE_SEPARATOR);
    }

    public void findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            Ui.printMessageWithSeparator("No matching tasks found.");
        } else {
            System.out.println(Ui.LINE_SEPARATOR);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                Task task = foundTasks.get(i);
                System.out.println((i + 1) + ". " + task.getDescription());
            }
            System.out.println(Ui.LINE_SEPARATOR);
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

