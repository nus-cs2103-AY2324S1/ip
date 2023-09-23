package dogebot;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

/**
 * The TaskList class contains the task list and operations for tasks.
 *
 * @author Kenvyn Kwek
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Initializes TaskList object with an ArrayList of Task objects as input.
     *
     * @param tasklist Tasklist of tasks.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasks = tasklist;
    }

    /**
     * Getter to retrieve an ArrayList of Task objects.
     *
     * @return tasks in an ArrayList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists out current tasks.
     *
     * @throws DogeBotException If tasklist is empty.
     */
    public static String list() throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Your list is empty ! Try adding some tasks first c:");
        }

        StringBuilder result = new StringBuilder("Stuff to do:\n");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            result.append(i++ + ". " + task.toString() + "\n");
        }
        return result.toString();
    }

    /**
     * Marks a task with its index as input.
     *
     * @param input User input for mark command.
     * @return Output from processing mark command.
     * @throws DogeBotException If tasklist is empty.
     */
    public static String mark(String input) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        StringBuilder result = new StringBuilder();
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        // assertion: check if index <= tasklist size
        assert index < tasks.size() : "Index to mark is too big";

        tasks.get(index).markTask(true);
        result.append("Good job on completing a task ! You deserve a cookie C:\n");
        result.append("\t" + tasks.get(index).toString());

        return result.toString();
    }

    /**
     * Unmarks a task with its index as input.
     *
     * @param input User input for unmark command.
     * @return Output from processing unmark command.
     * @throws DogeBotException If tasklist is empty.
     */
    public static String unmark(String input) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        StringBuilder result = new StringBuilder();
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        // assertion: check if index <= tasklist size
        assert index < tasks.size() : "Index to unmark is too big";

        tasks.get(index).markTask(false);
        result.append("Oh nyo, did someone make a mistake ?\n");
        result.append("\t" + tasks.get(index).toString());

        return result.toString();
    }

    /**
     * Gets current number of tasks.
     *
     * @return Number of tasks.
     */
    public static String updateTasksCounter() {
        return "You now have " + tasks.size() + " task(s) in your list";
    }

    /**
     * Creates a new ToDos task from user input.
     *
     * @param input User input for a ToDos task.
     * @return Output from processing a ToDos task.
     * @throws DogeBotException If input in empty.
     */
    public static String todo(String input) throws DogeBotException {
        String[] split = input.split("todo ");
        if (split.length == 0) {
            throw new DogeBotException("Oops ! The description of a todo cannot be empty :(");
        }

        String taskDescription = split[1];
        StringBuilder result = new StringBuilder();
        result.append("Mama mia ! I've just added this task:\n");
        Task temp = new ToDos(taskDescription, false);
        tasks.add(temp);
        result.append("\t" + temp.toString() + "\n");
        result.append(updateTasksCounter());

        return result.toString();
    }

    /**
     * Creates new Deadline task from user input.
     *
     * @param input User input for a deadline task.
     * @return Output from processing a deadline task.
     * @throws DogeBotException If input is empty.
     */
    public static String deadline(String input) throws DogeBotException {
        String[] split = input.split("deadline ");
        if (split.length == 0) {
            throw new DogeBotException("Oops ! The description of a deadline cannot be empty :(");
        }

        StringBuilder result = new StringBuilder();
        result.append("Mama mia ! I've just added this task:\n");

        String taskDescription = input.split("deadline ")[1].split(" /by")[0];
        String taskDeadline = input.split("/by ")[1];

        Task temp = new Deadline(taskDescription, taskDeadline, false);
        tasks.add(temp);
        result.append("\t" + temp.toString() + "\n");
        result.append(updateTasksCounter());

        return result.toString();
    }

    /**
     * Creates new Event task from user input.
     *
     * @param input User input for an event task.
     * @return Output from processing an event task.
     * @throws DogeBotException If input is empty.
     */
    public static String event(String input) throws DogeBotException {
        String[] split = input.split("event ");
        if (split.length == 0) {
            throw new DogeBotException("Oops ! The description of an event cannot be empty :(");
        }

        StringBuilder result = new StringBuilder();

        String taskDescription = input.split("event ")[1].split(" /from")[0];
        String start = input.split("/from ")[1].split(" /to")[0];
        String end = input.split("/to ")[1];

        result.append("Mama mia ! I've just added this task:\n");
        Task temp = new Event(taskDescription, start, end, false);
        tasks.add(temp);
        result.append("\t" + temp.toString() + "\n");
        result.append(updateTasksCounter());

        return result.toString();
    }

    /**
     * Deletes a task with index from user input.
     *
     * @param input User input to delete a task.
     * @return Output from processing task deletion.
     * @throws DogeBotException If task list is empty.
     */
    public static String delete(String input) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! There's no tasks in your list to delete :O");
        }

        StringBuilder result = new StringBuilder();
        int index = Integer.parseInt(input.split(" ")[1]) - 1;

        // assertion: check if index > tasklist size
        assert index < tasks.size() : "Task index to delete is too big";

        Task curr = tasks.get(index);
        result.append("Got it~ This task has been removed:\n");
        result.append("\t" + curr.toString() + "\n");
        tasks.remove(index);
        result.append(updateTasksCounter());

        return result.toString();
    }

    /**
     * Retrieves tasks with the input keyword.
     *
     * @param input User input with keyword.
     * @return The list of tasks with input keyword.
     * @throws DogeBotException If input keyword is empty.
     */
    public static String find(String input) throws DogeBotException {
        String[] split = input.split("find ");
        if (split.length == 0) {
            throw new DogeBotException("Oops ! The keyword cannot be empty :(");
        }
        String keyword = input.split(" ")[1];
        ArrayList<Task> found = new ArrayList<>();
        for (Task task : tasks) {
            if (task.hasWord(keyword)) {
                found.add(task);
            }
        }

        if (found.size() == 0) {
            return "Oh nyo, looks like there's no tasks matching that keyword :(";
        }

        StringBuilder result = new StringBuilder();
        result.append("Found it~ Here are your matching tasks:\n");
        int i = 1;
        for (Task task : found) {
            result.append(i++ + ". " + task.toString() + "\n");
        }

        return result.toString();
    }
}
