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
     * @return The list of tasks.
     */
    public String printTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            output += index + "." + this.tasks.get(i).toString() + "\n";
        }

        return output;
    }

    /**
     * Handles the adding of a task.
     * @return Acknowledgement.
     */
    public String handleTask(String task) throws DukeException {
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            nextTaskString = handleTodo(task);
        } else if (task.startsWith("deadline")) {
            nextTaskString = handleDeadline(task);
        } else if (task.startsWith("event")) {
            nextTaskString = handleEvent(task);
        }

        String output = "Got it. I've added this task:\n";
        output += nextTaskString + "\n";
        output += "Now you have " + this.tasks.size() + " tasks in the list.";

        return output;
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
    public String handleDelete(String nextTask) throws DukeException {
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

        String output = "Noted. I've removed this task:\n";
        output += taskToRemove.toString() + "\n";
        output += "Now you have " + this.tasks.size() + " tasks in the list.\n";

        return output;
    }

    /**
     * Handles the marking of a task.
     */
    public String handleMark(String nextTask) throws DukeException {
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
            return this.markTask(taskIndex);
        } else {
            return this.unmarkTask(taskIndex);
        }
    }

    private String markTask(int taskIndex) {
        this.tasks.get(taskIndex).doTask();


        String output = "Nice! I've marked this task as done:\n";
        output += this.tasks.get(taskIndex).toString() + "\n";

        return output;
    }

    private String unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).undoTask();

        String output = "OK, I've marked this task as not done yet:\n";
        output += this.tasks.get(taskIndex).toString() + "\n";

        return output;
    }

    /**
     * Finds the specified task.
     */
    public String find(String task) throws DukeException {
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
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < similarTasks.size(); i++) {
                output += i + 1 + ". " + similarTasks.get(i).toString() + "\n";
            }

            return output;
        }
    }
}
