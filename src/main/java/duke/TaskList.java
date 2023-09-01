package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int counter = 0;

    public void mark(int i) {
        tasks.get(i-1).setDone();
        System.out.println("Nice! I've marked this task as done:\n  "
                + tasks.get(i-1).toString());
    }

    public void unmark(int i) {
        tasks.get(i-1).setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n  "
                + tasks.get(i-1).toString());
    }

    public void list() {
        if (counter == 0) {
            System.out.println("There is no task in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= counter; i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
        counter += 1;
        saveTask(t);
        System.out.println("Got it. I've added this task:\n  "
                + t + "\nNow you have " + counter + " tasks in the list.");
    }

    public void removeTask(int index) {
        String temp = tasks.get(index-1).toString();
        tasks.remove(index-1);
        counter -= 1;
        System.out.println("Noted. I've removed this task:\n"
                + temp + "\nNow you have " + counter + " tasks in the list.");
    }

    public void saveTask(Task t) {
        Taskmanager.storage.write(t.getSavingFormat());
    }

    public void readTask(String key) {
        if (Taskmanager.storage == null) {
            System.out.println("There is no previously saved task!");
        } else {
            System.out.println(Taskmanager.storage.read(key));
        }
    }
}
