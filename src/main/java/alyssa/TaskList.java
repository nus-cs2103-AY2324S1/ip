package alyssa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import alyssa.exceptions.AlyssaArgumentException;
/**
 * This class supports Alyssa by encapsulating the current tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor method for TaskList if an existing List of Tasks is present.
     * @param taskList The existing List of Tasks to be used.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    /**
     * Constructor method for TaskList. This TaskList is instantiated with no existing tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    protected String listTasks() {
        int counter = 1;
        String response = "Here are the tasks in your list:\n";
        for (Task task : taskList) {
            response += counter + "." + task.toString() + "\n";
            counter++;
        }
        return response;
    }

    /**
     * Marks a provided task as done.
     * @param rest The number of the task to be marked, given as a String.
     * @throws AlyssaArgumentException AlyssaArgumentException is thrown if the task number is invalid.
     * @throws NumberFormatException NumberFormatException is thrown if the provided String is not an int.
     */
    protected String markTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsDone();
        String output = "Nice! I've marked this task as done:\n";
        output += task.toString();
        return output;
    }
    /**
     * Marks a provided task as undone.
     * @param rest The number of the task to be unmarked, given as a String.
     * @throws AlyssaArgumentException AlyssaArgumentException is thrown if the task number is invalid.
     * @throws NumberFormatException NumberFormatException is thrown if the provided String is not an int.
     */
    protected String unmarkTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task task = taskList.get(index - 1);
        task.markAsUndone();
        String output = "OK, I've marked this task as not done yet:";
        return output + task.toString();
    }

    /**
     * Adds a given todo.
     * @param desc Description of the todo.
     * @throws AlyssaArgumentException AlyssaArgumentException is thrown if the description is empty.
     */
    protected String addTodo(String desc) throws AlyssaArgumentException {
        if (desc.isEmpty()) {
            throw new AlyssaArgumentException("Please specify a description for the todo.");
        }
        Task newTodo = new Todo(desc);
        taskList.add(newTodo);
        String output = "Got it. I've added this task:\n";
        output += newTodo.toString() + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * Adds a given deadline.
     * @param rest Details of the deadline, provided as a String.
     * @throws AlyssaArgumentException AlyssaArgumentException is
     *     thrown if the String given is not of the correct format.
     */
    protected String addDeadline(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /by ");
        if (parsed.length < 2) {
            throw new AlyssaArgumentException("Incorrect deadline syntax. Syntax: deadline desc /by date");
        }
        String desc = parsed[0];
        String by = parsed[1];
        Task newDeadline;
        try {
            newDeadline = new Deadline(desc, LocalDate.parse(by));
        } catch (DateTimeParseException e) {
            throw new AlyssaArgumentException("Invalid by. Syntax: yyyy-mm-dd");
        }
        taskList.add(newDeadline);
        String output = "Got it. I've added this task:\n";
        output += newDeadline.toString() + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.\n";
        return output;
    }

    /**
     * Adds an event to the TaskList.
     * @param rest Details of the event, in the form of a String.
     * @throws AlyssaArgumentException AlyssaArgumentException is thrown
     *      if the String given is not of the correct format.
     */
    protected String addEvent(String rest) throws AlyssaArgumentException {
        String[] parsed = rest.split(" /from | /to ");
        if (parsed.length < 3) {
            throw new AlyssaArgumentException("Incorrect event syntax. Syntax: event desc /from date /to date");
        }
        String desc = parsed[0];
        String from = parsed[1];
        String to = parsed[2];
        Task newEvent = new Event(desc, from, to);
        taskList.add(newEvent);
        String output = "Got it. I've added this task:\n";
        output += newEvent.toString() + "\n";
        output += "Now you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * Deletes a task in the TaskList.
     * @param rest Position of the task to be deleted.
     * @throws AlyssaArgumentException AlyssaArgumentException is thrown if the task number is invalid.
     * @throws NumberFormatException NumberFormatException is thrown if rest is not an int.
     */
    protected String deleteTask(String rest) throws AlyssaArgumentException, NumberFormatException {
        int index = Integer.valueOf(rest); //this could throw a NumberFormatException if rest is not a number
        if (index < 1 || index > taskList.size()) {
            throw new AlyssaArgumentException("Invalid task number");
        }
        Task toDelete = taskList.get(index - 1);
        String output = "Noted. I've removed this task:\n";
        output += toDelete.toString() + "\n";
        taskList.remove(index - 1);
        output += "Now you have " + taskList.size() + " tasks in the list.";
        return output;
    }

    /**
     * Returns the encapsulated task list as a List of Tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }
    protected String printRelevantTasks(String searchKey) {
        List<String> toPrint = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.getDescription().contains(searchKey)) {
                toPrint.add((i + 1) + "." + task.toString());
            }
        }
        String output = "Here are the matching tasks in your list:\n";
        for (String relevantTask : toPrint) {
            output += relevantTask + "\n";
        }
        return output;
    }
}
