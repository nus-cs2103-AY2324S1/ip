package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

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
     * @return Lists of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Prints the list of tasks.
     * @return The list of tasks in String format.
     */
    public String printTasks() {
        String output = "Here are the tasks in your list:\n";

        assert (tasks != null) : "A TaskList must be instantiated even if there are no outstanding tasks.";

        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            output += index + "." + this.tasks.get(i).toString() + "\n";
        }

        return output;
    }

    /**
     * Handles the adding of a task.
     * @return Acknowledgement.
     * @param task Description of the task.
     * @throws DukeException DukeException is thrown if the task description is empty.
     */
    public String handleTask(String task) throws DukeException {
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            nextTaskString = handleTodo(task);
        } else if (task.startsWith("deadline")) {
            nextTaskString = handleDeadline(task);
        } else if (task.startsWith("event")) {
            nextTaskString = handleEvent(task);
        } else if (task.startsWith("recur")) {
            nextTaskString = handleRecur(task);
        }

        String output = "Got it. I've added this task:\n";
        output += nextTaskString + "\n";
        output += "Now you have " + this.tasks.size() + " tasks in the list.";

        return output;
    }

    /**
     * Handles the adding of a Todo task.
     * @throws DukeException DukeException is thrown if the task description is empty.
     */
    protected String handleTodo(String task) throws DukeException {
        assert (task != null) : "A valid todo command must be passed in.";

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
     * @param task Description of the Deadline task.
     * @throws DukeException DukeException is thrown if the task description or relevant information is empty.
     */
    protected String handleDeadline(String task) throws DukeException {
        assert (task != null) : "A valid deadline command must be passed in.";

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
     * @param task Description of the Event task.
     * @throws DukeException DukeException is thrown if the task description or relevant information is empty.
     */
    protected String handleEvent(String task) throws DukeException {
        assert (task != null) : "A valid event command must be passed in.";

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
     * Handles the adding of a Recurring task.
     * @param task Description of the Recurring task.
     * @throws DukeException DukeException is thrown if the task description or relevant information is empty.
     */
    protected String handleRecur(String task) throws DukeException {
        assert (task != null) : "A valid recur command must be passed in.";

        String[] temp = this.parser.parseNewTaskByType(task);

        System.out.println(Arrays.toString(temp));

        if (temp.length <= 1) {
            throw new DukeException("You are missing both a valid recurring description and a recurrence. "
                    + "Please enter a valid recurring description and recurrence.");
        }

        String[] preprocessedTask = this.parser.parseNewTaskByDate(temp[1]);

        System.out.println(Arrays.toString(preprocessedTask));

        if (preprocessedTask.length <= 1) {
            throw new DukeException("You are missing either a valid recurring description or recurrence. "
                    + "Please enter a valid recurring description or recurrence.");
        }

        Recurring nextRecur = new Recurring(preprocessedTask[0], preprocessedTask[1]);
        this.tasks.add(nextRecur);
        return nextRecur.toString();
    }

    /**
     * Handles the deletion of a task.
     * @param nextTask Description of the task.
     * @throws DukeException DukeException is thrown if the index is empty or invalid.
     */
    public String handleDelete(String nextTask) throws DukeException {
        assert (nextTask != null) : "A valid delete command must be passed in.";

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
     * @param nextTask Description of the task.
     * @throws DukeException DukeException is thrown if the index is empty or invalid.
     */
    public String handleMark(String nextTask) throws DukeException {
        assert (nextTask != null) : "A valid mark command must be passed in.";

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
     * @param task Description of the task to find.
     * @throws DukeException DukeException is thrown if the task to find is empty.
     */
    public String find(String task) throws DukeException {
        assert (task != null) : "A valid find command must be passed in.";

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
