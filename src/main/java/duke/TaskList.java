package duke;

import java.util.ArrayList;

/**
 * Various commands to modify the tasks inside the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Marks a task as done by adding X.
     *
     * @param splitOutput User command.
     */
    public void mark(String[] splitOutput) {
        System.out.println("Nice! I've marked this task as done:");
        Task selectedTask = tasks.get(Integer.parseInt(splitOutput[1]) - 1);
        selectedTask.mark();
        System.out.println(selectedTask.toString());
    }

    /**
     * Unmarks a task as not done by removing X.
     *
     * @param splitOutput User command.
     */
    public void unmark(String[] splitOutput) {
        System.out.println(" OK, I've marked this task as not done yet:");
        Task selectedTask = tasks.get(Integer.parseInt(splitOutput[1]) - 1);
        selectedTask.unmark();
        System.out.println(selectedTask.toString());
    }

    /**
     * Deletes a task.
     *
     * @param splitOutput User command.
     */
    public void delete(String[] splitOutput) {
        try {
            Integer deleteIndex = Integer.valueOf(splitOutput[1]);
            Task deletedTask = tasks.get(deleteIndex - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            tasks.remove(deleteIndex - 1);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Lists out the tasks stored in the task list.
     */
    public void list() {
        if (tasks.isEmpty()) {
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    public void find(String[] splitOutput) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(splitOutput[1])) {
                filteredList.add(tasks.get(i));
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.println(i + 1 + ". " + filteredList.get(i).toString());
        }
    }
}
