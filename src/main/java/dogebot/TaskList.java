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
    public static void list() throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Your list is empty ! Try adding some tasks first c:");
        }

        System.out.println("Stuff to do:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(i++ + ". " + task.toString());
        }
    }

    /**
     * Marks a task with its index as input.
     *
     * @param index Index of task to mark.
     * @throws DogeBotException If tasklist is empty.
     */
    public static void mark(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        tasks.get(index).markTask(true);
        System.out.println("Good job on completing a task ! You deserve a cookie C:");
        System.out.println("\t" + tasks.get(index).toString());
    }

    /**
     * Unmarks a task with its index as input.
     *
     * @param index Index of task to unmark.
     * @throws DogeBotException If task list is empty.
     */
    public static void unmark(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        tasks.get(index).markTask(false);
        System.out.println("Oh nyo, did someone make a mistake ?");
        System.out.println("\t" + tasks.get(index).toString());
    }

    /**
     * Prints current number of tasks.
     */
    public static void updateTasksCounter() {
        System.out.println("You now have " + tasks.size() + " task(s) in your list");
    }

    /**
     * Creates a new ToDos task.
     *
     * @param words User command for todos task.
     * @throws DogeBotException If input is empty.
     */
    public static void todo(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of a todo cannot be empty :(");
        }

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new ToDos(words, false);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    /**
     * Creates new Deadline task.
     *
     * @param words User command for deadline task.
     * @throws DogeBotException If input is empty.
     */
    public static void deadline(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of a deadline cannot be empty :(");
        }

        int split = words.indexOf("/by");
        // substring w/o the spaces
        String taskDescription = words.substring(0, split - 1);
        String taskDeadline = words.substring(split + 4, words.length());

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new Deadline(taskDescription, taskDeadline, false);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    /**
     * Creates new Event task.
     *
     * @param words User command for event task.
     * @throws DogeBotException If input is empty.
     */
    public static void event(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of an event cannot be empty :(");
        }

        // substring w/o the spaces
        int startSplit = words.indexOf("/from");
        String taskDescription = words.substring(0, startSplit - 1);
        int endSplit = words.indexOf("/to", startSplit + 1); // find "/" after startSplit index
        String start = words.substring(startSplit + 6, endSplit - 1);
        String end = words.substring(endSplit + 4, words.length());

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new Event(taskDescription, start, end, false);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    /**
     * Deletes a task with its index as input.
     *
     * @param index Index of task to delete.
     * @throws DogeBotException If task list is empty.
     */
    public static void delete(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! There's no tasks in your list to delete :O");
        }

        Task curr = tasks.get(index);
        System.out.println("Got it~ This task has been removed:");
        System.out.println("\t" + curr.toString());
        tasks.remove(index);
        updateTasksCounter();
    }
}
