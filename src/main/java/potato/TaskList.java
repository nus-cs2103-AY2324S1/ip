package potato;

import java.io.IOException;
import java.util.ArrayList;

import potato.task.*;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void mark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(index).setStatus(true);
        System.out.println("Slayyy such efficiency we love it!\n"
                + "Let's take this off the list!\n"
                + tasks.get(index).toString());
        storage.saveTask(tasks);
    }

    public void unmark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(index).setStatus(false);
        System.out.println("Ohmah... not you lying to me??\n"
                + "This is the last time I'm adding it back for you!\n"
                + tasks.get(index).toString());
        storage.saveTask(tasks);
    }

    public void delete(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        System.out.println("NAUR NOT THE DITCHING?!\n" + "It's a no do for\n"
                + tasks.get(index).toString() + "\n" + "Now there's only " + (tasks.size() - 1)
                + " tasks left sistar pls do them.");
        tasks.remove(index);
        storage.saveTask(tasks);
    }

    public void list() {
        System.out.println("Ok look all you want but they literally won't do themselves?");

        int count = 0;
        for (Task t : tasks) {
            if (t == null) {
                break;
            }
            count++;
            System.out.println(String.valueOf(count) + "." + t.toString());
        }
    }

    public void find(String input) throws IOException {
        String s = input.substring(5);
        if (tasks.size() == 0) {
            System.out.println("Nope nothing here...Input tasks first!");
        } else {
            int count = 0;
            for (Task t : tasks) {
                if (t.getDescription().contains(s)) {
                    count++;
                    System.out.println(String.valueOf(count) + "." + t.toString());
                }
            }
            System.out.println("Ok that's all I found...");
        }
    }

    public void add(String input, Storage storage) throws IOException {
        Task task = Task.parse(input);

        if (task == null) {
            new PotatoException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        } else {
            tasks.add(task);
            storage.saveTask(tasks);
            int size = tasks.size();
            System.out.println("Let's add this to the never ending pile...\n"
                    + tasks.get(size - 1).toString() + "\n"
                    + "Now you have " + size + " tasks in the list.");
        }
    }

}
