package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class stores tasks.
 */
public class TaskList {
    private final String line = "____________________________________________________________";
    private ArrayList<Task> tasks;
    private Parser parser;

    /**
     * Constructor method for a TaskList.
     * @param tasks A list of the current tasks recorded
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.parser = new Parser();
    }

    /**
     * Returns the list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Prints the list of tasks.
     */
    public void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            System.out.println(index + "." + this.tasks.get(i).toString());
        }
        System.out.println(line);
    }

    /**
     * Handles the adding of a task.
     */
    public void handleTask(String task) throws DukeException {
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            nextTaskString = handleTodo(task);
        } else if (task.startsWith("deadline")) {
            nextTaskString = handleDeadline(task);
        } else if (task.startsWith("event")) {
            nextTaskString = handleEvent(task);
        }

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTaskString);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Handles the adding of a Todo task.
     */
    protected String handleTodo(String task) throws DukeException {
        String[] preprocessedTask = this.parser.parseNewTaskByType(task);

        if (preprocessedTask.length <= 1) {
            throw new DukeException("Please enter a valid todo description.");
        }


        Todo nextTodo = new Todo(preprocessedTask[1]);
        this.tasks.add(nextTodo);
        return nextTodo.toString();
    }

    /**
     * Handles the adding of a Deadline task.
     */
    protected String handleDeadline(String task) throws DukeException {
        String[] temp = this.parser.parseNewTaskByType(task);

        if (temp.length <= 1) {
            throw new DukeException("You are missing both a valid deadline description and a deadline. "
                    + "Please enter a valid deadline description and deadline.");
        }

        String[] preprocessedTask = this.parser.parseNewTaskByDate(temp[1]);

        if (preprocessedTask.length <= 1) {
            throw new DukeException("You are missing either a valid deadline description or deadline. "
                    + "Please enter a valid deadline description or deadline.");
        }

        LocalDate deadline;
        try {
            deadline = LocalDate.parse(preprocessedTask[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please ensure that the deadline provided is in YYYY-MM-DD format.");
        }

        Deadline nextDeadline = new Deadline(preprocessedTask[0], deadline);
        this.tasks.add(nextDeadline);
        return nextDeadline.toString();
    }

    /**
     * Handles the adding of an Event task.
     */
    protected String handleEvent(String task) throws DukeException {
        String[] temp = this.parser.parseNewTaskByType(task);

        if (temp.length <= 1) {
            throw new DukeException("You are missing both a valid event description and a start and end time. "
                    + "Please enter a valid event description and start and end time.");
        }

        String[] preprocessedTask = this.parser.parseNewTaskByDate(temp[1]);

        if (preprocessedTask.length <= 2) {
            throw new DukeException("You are missing either a valid event description or start and end time. "
                    + "Please enter a valid event description or start and end time.");
        }



        Event nextEvent = new Event(preprocessedTask[0], preprocessedTask[1], preprocessedTask[2]);
        this.tasks.add(nextEvent);
        return nextEvent.toString();
    }

    /**
     * Handles the deletion of a task.
     */
    public void handleDelete(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to delete.");
        }

        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to delete.");
        }

        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        Task taskToRemove = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);

        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Handles the marking of a task.
     */
    public void handleMark(String nextTask) throws DukeException {
        String[] split = nextTask.split(" ");

        if (split.length <= 1) {
            throw new DukeException("You did not enter an index. Please enter a valid index to mark or unmark.");
        }

        String action = split[0];
        String index = split[1];

        int taskIndex = -1;

        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("You did not enter a valid index. Please enter a valid index to mark or unmark.");
        }


        if (taskIndex < 0 || taskIndex >= this.tasks.size()) {
            throw new DukeException("There is no such task index. Please enter a valid index.");
        }

        if (action.equals("mark")) {
            this.markTask(taskIndex);
        } else if (action.equals("unmark")) {
            this.unmarkTask(taskIndex);
        }
    }

    private void markTask(int taskIndex) {
        this.tasks.get(taskIndex).doTask();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    private void unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).undoTask();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    /**
     * Finds the specified task.
     */
    public void find(String task) throws DukeException {
        String[] split = task.split("find ");

        if (split.length <= 1) {
            throw new DukeException("Please enter a task to find");
        }

        String taskToFind = split[1];

        ArrayList<Task> similarTasks = new ArrayList<Task>();
        for (Task recordedTasks : tasks) {
            if (recordedTasks.getTaskDescription().contains(taskToFind)) {
                similarTasks.add(recordedTasks);
            }
        }

        if (similarTasks.isEmpty()) {
            throw new DukeException("There is no such task.");
        } else {
            System.out.println(line);
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < similarTasks.size(); i++) {
                System.out.println(i + 1 + ". " + similarTasks.get(i).toString());
            }
            System.out.println(line);
        }
    }
}
