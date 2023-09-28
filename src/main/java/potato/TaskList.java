package potato;

import java.io.IOException;
import java.util.ArrayList;

import potato.task.*;

/**
 * The TaskList class represents a list of tasks and provides methods
 * for managing and manipulating those tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task as completed.
     *
     * @param input   The user input specifying the task index to be marked.
     * @param storage The storage utility for saving task changes.
     * @return A message indicating the task has been marked as completed.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public String mark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        int len = tasks.size();
        if (index < 0) {
            throw new PotatoException("Who starts counting from before 1??");
        } else if (index >= len) {
            throw new PotatoException("Pls you don't have thaaaat many tasks...\n"
                    + "There's only " + len + " task(s) in the list");
        }
        tasks.get(index).setStatus(true);
        String s = "Slayyy such efficiency we love it!\n"
                + "Let's take this off the list!\n"
                + tasks.get(index).toString();
        storage.saveTask(tasks);
        return s;
    }

    /**
     * Unmarks a completed task.
     *
     * @param input   The user input specifying the task index to be unmarked.
     * @param storage The storage utility for saving task changes.
     * @return A message indicating the task has been unmarked.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public String unmark(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        int len = tasks.size();
        if (index < 0) {
            throw new PotatoException("Who starts counting from before 1??");
        } else if (index >= len) {
            throw new PotatoException("Pls you don't have thaaaat many tasks...\n"
                    + "There's only " + len + " task(s) in the list");
        }
        tasks.get(index).setStatus(false);
        String s = "Ohmah... not you lying to me??\n"
                + "This is the last time I'm adding it back for you!\n"
                + tasks.get(index).toString();
        storage.saveTask(tasks);
        return s;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input   The user input specifying the task index to be deleted.
     * @param storage The storage utility for saving task changes.
     * @return A message indicating the task has been deleted.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public String delete(String input, Storage storage) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        int len = tasks.size();
        if (index < 0) {
            throw new PotatoException("Who starts counting from before 1??");
        } else if (index >= len) {
            throw new PotatoException("Pls you don't have thaaaat many tasks...\n"
                    + "There's only " + len + " task(s) in the list");
        }
        String s = "NAUR NOT THE DITCHING?!\n" + "It's a no do for\n"
                + tasks.get(index).toString() + "\n" + "Now there's only " + (tasks.size() - 1)
                + " tasks left sistar pls do them.";
        tasks.remove(index);
        storage.saveTask(tasks);
        return s;
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return A string containing a list of all tasks.
     */
    public String list() {
        String s = "Ok look all you want but they literally won't do themselves?\n";
        if (tasks.size() == 0) {
            s = "Okay there's nothing to do now stop staring.";
        }
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

    /**
     * Finds tasks containing a specified keyword in their description.
     *
     * @param input The user input specifying the keyword to search for.
     * @return A string containing a list of tasks matching the keyword.
     */
    public String find(String input) {
        String keyword = input.substring(5);
        String s = "";
        if (tasks.size() == 0) {
            throw new PotatoException("There's nothing to find from...\nPls add some tasks first sistar...");
        }
        int count = 0;
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                s += String.valueOf(count) + "." + t.toString() + "\n";
            }
        }
        if (count == 0) {
            s = "[" + keyword + "] matches nothing in your list...";
        } else {
            s += "OMG I found " + count + " bombastic tasks in the list!";
        }
        return s;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param input   The user input specifying the task description to be added.
     * @param storage The storage utility for saving task changes.
     * @return A message indicating the task has been added to the list.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public String add(String input, Storage storage) throws IOException {
        try {
            Task task = Task.parse(input);
            String s = "Let's add this to the never ending pile...\n";
            tasks.add(task);
            storage.saveTask(tasks);
            int size = tasks.size();
            s += tasks.get(size - 1).toString() + "\n"
                    + "Now you have " + size + " task(s) in the list.";
            return s;
        } catch (PotatoException e) {
            return e.getMessage();
        }

    }

    /**
     * Sets the priority of a task.
     *
     * @param input   The user input specifying the task index and priority to set.
     * @param storage The storage utility for saving task changes.
     * @return A message indicating the task's priority has been set.
     * @throws IOException If an error occurs while saving tasks to storage.
     */
    public String setPriority(String input, Storage storage) throws IOException {
        int indexSet = input.indexOf("#");
        if (indexSet < 0) {
            throw new PotatoException("Input your priority like #LEVEL gurl");
        }
        int index = Integer.parseInt(input.substring(9, indexSet - 1)) - 1;
        String priority = input.substring(indexSet + 1);
        tasks.get(index).setPriority(priority);
        String s = "FANTASTIC! This task\n"
                + tasks.get(index).toString() + "\n"
                + "is on " + priority.toUpperCase() + " priority now :D";
        storage.saveTask(tasks);
        return s;
    }

    public String clear() {
        tasks = new ArrayList<Task>();
        return "ABRACADABRA POOF There's suddenly nothing left to do!";
    }
}
