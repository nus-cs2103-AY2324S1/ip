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

    public String mark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(index).setStatus(true);
        String s = "Slayyy such efficiency we love it!\n"
                + "Let's take this off the list!\n"
                + tasks.get(index).toString();
        storage.saveTask(tasks);
        return s;
    }

    public String unmark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(index).setStatus(false);
        String s = "Ohmah... not you lying to me??\n"
                + "This is the last time I'm adding it back for you!\n"
                + tasks.get(index).toString();
        storage.saveTask(tasks);
        return s;
    }

    public String delete(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        String s = "NAUR NOT THE DITCHING?!\n" + "It's a no do for\n"
                + tasks.get(index).toString() + "\n" + "Now there's only " + (tasks.size() - 1)
                + " tasks left sistar pls do them.";
        tasks.remove(index);
        storage.saveTask(tasks);
        return s;
    }

    public String list() {
        String s = "Ok look all you want but they literally won't do themselves?\n";

        int count = 0;
        for (Task t : tasks) {
            if (t == null) {
                break;
            }
            count++;
            s += String.valueOf(count) + "." + t.toString() + "\n";
        }
        return s;
    }

    public String find(String input) {
        String keyword = input.substring(5);
        String s = "";
        if (tasks.size() == 0) {
            s += "Nope nothing here...Input tasks first!";
        } else {
            int count = 0;
            for (Task t : tasks) {
                if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    count++;
                    s += String.valueOf(count) + "." + t.toString() + "\n";
                }
            }
            s += "Ok that's all I found...";
        }
        return s;
    }

    public String add(String input, Storage storage) throws IOException {
        Task task = Task.parse(input);
        String s = "";

        if (task == null) {
            new PotatoException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        } else {
            tasks.add(task);
            storage.saveTask(tasks);
            int size = tasks.size();
            s += "Let's add this to the never ending pile...\n"
                    + tasks.get(size - 1).toString() + "\n"
                    + "Now you have " + size + " tasks in the list.";
        }
        return s;
    }
}
