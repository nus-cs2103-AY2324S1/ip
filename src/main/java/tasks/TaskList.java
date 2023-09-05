package tasks;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.ArrayList;

/**
 * This class encapsulates a TaskList that stores all the Task objects
 * the user has created, and handles updating of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(0);
    }

    /**
     * Constructs a TaskList object that creates Task objects and
     * stores them in the task list.
     *
     * @param content A String containing information of the Task objects to be added.
     */
    public TaskList(String content) {
        this.tasks = new ArrayList<>(0);

        addTextToTask(content);
    }

    /**
     * Converts the String to Task objects to be added to the task list.
     *
     * @param content A String containing information of the Task objects to be added.
     */
    public void addTextToTask(String content) {
        String[] lines = content.split("\n");

        for (String line: lines) {
            String[] parts = line.split(" ", 3);
            if (parts[0].equals("T")) {

                ToDo task = new ToDo(parts[2]);
                this.tasks.add(task);

            } else if (parts[0].equals("D")) {

                String[] arr = parts[2].split("/");
                Deadline task = new Deadline(arr[0], arr[1]);
                this.tasks.add(task);

            } else {

                String[] arr = parts[2].split("/");
                Event task = new Event(arr[0], arr[1], arr[2]);
                this.tasks.add(task);

            }
        }

    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Prints onto the console all tasks on the current task list.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:\n");

        if (tasks.size() == 0) {
            System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
        }
    }


    /**
     * Sets a particular task to 'Done' status.
     *
     * @param taskNumber The task to be set to 'Done' based on the order of the task list. Starts at 1.
     * @throws IndexOutOfBoundsException
     */
    public void markTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsDone();

        System.out.println("Yay! You have completed this task:\n" +
                tasks.get(taskNumber - 1) + "\n");
    }

    /**
     * Sets a particular task to 'Not Done' status.
     *
     * @param taskNumber The task to be set to 'Not Done' based on the order of the task list. Starts at 1.
     * @throws IndexOutOfBoundsException
     */
    public void unmarkTaskDone(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        tasks.get(taskNumber - 1).setIsNotDone();

        System.out.println("Ok... Guess you're not actually done with this:\n" +
                tasks.get(taskNumber - 1) + "\n");
    }

    /**
     * Deletes a Task from the task list.
     *
     * @param taskNumber The task to be set to deleted based on the order of the task list. Starts at 1.
     * @throws IndexOutOfBoundsException
     */
    public void deleteTask(int taskNumber) throws IndexOutOfBoundsException {

        if (taskNumber < 0 || taskNumber > tasks.size()) {
            throw new IndexOutOfBoundsException("No such task exists!");
        }

        Task removedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        System.out.println("banished this task to the shadow realm:\n" + removedTask);
        printNumTasks();
    }

    /**
     * Adds a ToDo task object to the task list.
     *
     * @param description Description of the ToDo object.
     */
    public void addTodoTask(String description) {

        ToDo todo = new ToDo(description);
        this.tasks.add(todo);

        System.out.println("added new task:\n" + todo);
        printNumTasks();
    }

    /**
     * Adds a Deadline task object to the task list.
     *
     * @param description Description of the Deadline object.
     * @param end end Date/Time of the Deadline object.
     */
    public void addDeadlineTask(String description, String end) {

        Deadline deadline = new Deadline(description, end);
        this.tasks.add(deadline);

        System.out.println("added new task:\n" + deadline);
        printNumTasks();
    }

    /**
     * Adds an Event task object to the task list.
     *
     * @param description Description of the Event object.
     * @param start start Date/Time of the Event object.
     * @param end end Date/Time of the Event object.
     */
    public void addEventTask(String description, String start, String end) {

        Event event = new Event(description, start, end);
        this.tasks.add(event);

        System.out.println("added new task:\n" + event);
        printNumTasks();
    }

    public ArrayList<Task> findByKeyword(String keyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.description;
            if (taskDescription.contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Prints onto the console the number of tasks currently in the task list.
     */
    public void printNumTasks() {
        System.out.println("you now have " + tasks.size() + " tasks in your list." + "\n");
    }
}
