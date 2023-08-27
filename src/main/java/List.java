import java.util.ArrayList;

/**
 * The List class represents a collection of tasks managed by the ChatterChicken task manager.
 * This class is responsible for adding, marking, unmarking, and deleting tasks, as well as printing
 * the list of tasks along with their respective indexes.
 * Tasks can be of different types: ToDo, Deadline, and Event.
 */
public class List {
    private ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list based on the provided task type and input.
     * Depending on the task type (todo, deadline, event) a corresponding task is parsed from the input and
     * added to the list.
     *
     * @param type The type of the task to be added (todo, deadline, event).
     * @param input The input containing task details and information.
     * @throws CCException If there is an error in parsing the input or adding the task.
     */
    public void addTask(String type, String input) throws CCException {
        Task task = null;
        switch (type) {
        case "todo":
            task = parseToDo(input);
            break;
        case "deadline":
            task = parseDeadline(input);
            break;
        case "event":
            task = parseEvent(input);
            break;
        }
        list.add(task);
        System.out.println(ChatterChicken.LINE
                + ChatterChicken.INDENT + "Got it. I've added this task:\n"
                + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                + ChatterChicken.INDENT + "Now you have " + list.size() + " tasks in the list."
                + ChatterChicken.LINE);
    }

    /**
     * Parses the input string to create a new ToDo task.
     * The method extracts the task description from the input and creates a new ToDo task.
     *
     * @param input The input string containing the todo description.
     *              The input should be in the format "todo todo_description"
     * @return A new ToDo task object created from the provided input.
     * @throws CCException If the input string is empty or if there is an error in task creation.
     */
    private ToDo parseToDo (String input) throws CCException {
        if (input.equals("todo")) {
            throw new CCException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(input.substring("todo".length()).trim());
    }

    /**
     * Parses the input string to create a new Deadline task.
     * The method splits the input into fields, checks for the correct format, and extracts the task name
     * and deadline information to create a new Deadline task.
     *
     * @param input The input string containing the deadline description and end time.
     *              The input should be in the format "deadline deadline_description /by end_time"
     * @return A new Deadline task object created from the provided input.
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Deadline parseDeadline(String input) throws CCException {
        String[] fields = input.split("/");
        if (fields.length != 2) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        if (!fields[0].startsWith("deadline ") || !fields[1].startsWith("by ")) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].substring("deadline".length()).trim();
        String end = fields[1].substring("by".length()).trim();
        if (name.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for deadline detected.");
        }
        return new Deadline(name, end);
    }

    /**
     * Parses the input string to create a new Event task.
     * The method splits the input into fields, checks for the correct format, and extracts the task name,
     * start time, and end time information to create  a new Event task.
     *
     * @param input The input string containing the event description and start and end timings.
     *              The input should be in the format "event event_description /from start_time /to end_time
     * @return A new Event task object created from the provided input.
     * @throws CCException If the input format is incorrect or if there are empty fields.
     */
    private Event parseEvent(String input) throws CCException{
        String[] fields = input.split("/");
        if (fields.length != 3) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        if (!fields[0].startsWith("event ") || !fields[1].startsWith("from ") || !fields[2].startsWith("to ")) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        String name = fields[0].substring("event".length()).trim();
        String start = fields[1].substring("from".length()).trim();
        String end = fields[2].substring("to".length()).trim();
        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for event detected.");
        }
        return new Event(name, start, end);
    }

    /**
     * Marks a task as done based on the provided input.
     * The method attempts to retrieve the task from the list using the provided input, marks it as done,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to mark as done.
     *              The input should be in the format "mark task_index"
     * @throws CCException If there is an error in marking the task, or if the input is invalid.
     */
    public void markTask(String input) throws CCException {
        try {
            Task task = list.get(getIndex(input));
            task.setDone(true);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Nice! I've marked this task as done:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for marking list of length " + list.size());
        }
    }

    /**
     * Unmarks a previously marked task as not done based on the provided input.
     * The method attempts to retrieve the task from the list using the provided input, marks it as undone,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to unmark.
     *              The input should be in the format "unmark task_index"
     * @throws CCException If there is an error in unmarking the task, or if the input is invalid.
     */
    public void unmarkTask(String input) throws CCException {
        try {
            Task task = list.get(getIndex(input));
            task.setDone(false);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "OK, I've marked this task as not done yet:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + list.size());
        }
    }

    /**
     * Deletes a task from the task list based on the provided input.
     * The method attempts to retrieve the task index from the input, removes the task from the list,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to be deleted.
     *              The input should be in the format "delete task_index".
     * @throws CCException If there is an error in deleting the task or if the input is invalid.
     */
    public void deleteTask(String input) throws CCException {
        try {
            int index = getIndex(input);
            Task task = list.get(index);
            list.remove(index);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Noted. I've removed this task:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                    + ChatterChicken.INDENT + "Now you have " + list.size() + " tasks in your list."
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + list.size());
        }
    }

    /**
     * Retrieves the index of a task based on the provided input.
     *
     * @param input The input containing task information and index as the last character.
     * @return The index of the task parsed from the input.
     */
    private int getIndex(String input) {
        return input.charAt(input.length() - 1) - '0' - 1;
    }

    /**
     * Prints the list of tasks with their respective indexes.
     */
    public void printList() {
        System.out.println(ChatterChicken.LINE + ChatterChicken.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(ChatterChicken.INDENT_BIG + (i + 1) + "." + list.get(i).getTask());
        }
        System.out.println(ChatterChicken.LINE);
    }
}
