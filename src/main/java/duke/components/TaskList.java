package duke.components;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public static String mark(int index) throws DukeException {
        if (isValidIndex(index)) {
            tasks.get(index).setDone();
            return "Congratulations on finishing the task. I will now mark it as complete:\n" +
                    tasks.get(index).toString()+ "\n";
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static String unmark(int index) throws DukeException{
        if (isValidIndex(index)) {
            tasks.get(index).setNotDone();
            return "No worries. I will now mark it as incomplete:\n" +
                    tasks.get(index).toString() + "\n";
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public void delete(int index) {
        if (isValidIndex(index)) {
            tasks.remove(index);
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    public static String list() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) { // Generates the String representation of the list
            result += i + 1 + ". " + tasks.get(i) + "\n";
        }
        if (result != "Here are the tasks in your list:\n") {
            return result;
        } else { // Empty list
            return "There is nothing on your list currently. " +
                    "Perhaps you might want to add a new task?";
        }
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

}
