package duke.components;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public static void mark(int index) throws DukeException {
        if (isValidIndex(index)) {
            tasks.get(index).setDone();
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static void unmark(int index) throws DukeException{
        if (isValidIndex(index)) {
            tasks.get(index).setNotDone();
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public void delete(int index) throws DukeException {
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

    /**
     * Filters the list of task based on a given keyword. A new list will be returned for task containing the keyword.
     * @param keyword The task list will be filtered against this keyword.
     * @return A list of tasks that contains the keyword.
     */
    public String filter(String keyword) {
        String result = "Here are the tasks in your list containing " + keyword + ":\n";
        int counter = 0;
        for (Task task : tasks) { // Generates the String representation of the list
            String description = task.getDescription();
            if (description.contains(keyword)) {
                counter += 1;
                result += counter + ". " + task + "\n";
            }
        }
        if (counter > 0) {
            return result;
        } else { // Empty list
            return "There is nothing on your list currently that matches the keyword \"" + keyword + "\". " +
                    "Perhaps you might want to add a new task or try a different keyword?";
        }
    }

}
