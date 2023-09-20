package duke;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


/**
 * Class that contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor for a TaskList object.
     *
     * @param list List from storage for TaskList object to point to.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds given task to task list.
     *
     * @param task Task to be added.
     * @return Message indicating task was added
     */
    public String addTask(Task task) {
        this.list.add(task);
        return Ui.getAddTaskMessage(task, list);
    }

    /**
     * Extracts task index from user input and deletes the task.
     *
     * @param taskIndex The task index input by user.
     * @return Message indicating task was deleted.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public String deleteTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task deletedTask = list.get(index);
            assert index > 0;
            list.remove(index);
            return Ui.getDeleteTaskMessage(deletedTask, list);
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task index from user input and marks the task as done.
     *
     * @param taskIndex The task index input by user.
     * @return Message indicating task marked as done.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public String markTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).markAsDone();
            assert index > 0 && index < list.size();
            return Ui.getMarkedTaskMessage(list.get(index));
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task index from user input and marks the task as not done.
     *
     * @param taskIndex The task index input by user.
     * @return Message indicating task marked as not done.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public String unmarkTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).unMark();
            assert index > 0 && index < list.size();
            return Ui.getUnmarkTaskMessage(list.get(index));
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task description from user input and adds a todo task.
     *
     * @param input The text input by the user.
     * @return Message indicating todo was added.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public String addTodo(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input.trim());

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            // Todo description is empty
            throw new DukeException("OOPS!!! The description of a todo cannot be empty."
                    + "\ntodo {description}");
        }

        String description = matcher.group(1); // Extract task description
        Task task = new Todo(description);
        return addTask(task);

    }

    /**
     * Extracts task description, start time and end time from user input and adds an event task.
     *
     * @param input The text input by the user.
     * @return Message indicating event was added.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public String addEvent(String input) throws DukeException {
        // Define regular expressions for pattern matching for event
        Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from"
                + "\\s+(.*?)\\s+/to\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = eventPattern.matcher(input);

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            // User did not follow event format
            throw new DukeException("Input for event doesn't match the expected format."
                    + "\nevent {task} /from {startDate in YYYY-MM-DD} /to {endDate in YYYY-MM-DD}");
        }

        String description = matcher.group(1); // Extract event name
        LocalDate startDate = Parser.stringToDate(matcher.group(2)); // Extract start date
        LocalDate endDate = Parser.stringToDate(matcher.group(3)); // Extract end date
        Task task = new Event(startDate, endDate, description);
        return addTask(task);
    }

    /**
     * Extracts task description and due date from user input and adds a deadline task.
     *
     * @param input The text input by the user.
     * @return Message indicating deadline was added.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public String addDeadline(String input) throws DukeException {
        // Define regular expressions for pattern matching for deadline
        Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = deadlinePattern.matcher(input);

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            // User did not follow deadline format
            throw new DukeException("Input for deadline doesn't match the expected format."
                    + "\ndeadline {description} /by {endDate in YYYY-MM-DD}");
        }

        String description = matcher.group(1); // Extract task description
        LocalDate dueDate = Parser.stringToDate(matcher.group(2)); // Extract due date
        Task task = new Deadline(dueDate, description);
        return addTask(task);
    }

    /**
     * Returns all tasks in the list in a String format.
     *
     * @return All the tasks in the list as a string.
     */
    public String getList() {
        return Ui.getListAsString(list);
    }

    /**
     * Returns all the tasks in the current list that matches the target string as a string.
     *
     * @param target The search target to match to.
     * @return All the matching tasks as a string.
     */
    private String getMatchingTasks(String target) {
        StringBuilder s = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(target)) {
                s.append(counter + ". " + list.get(i) + "\n");
                counter++;
            }
        }
        return s.toString();
    }


    /**
     * Extracts the search target from the user input and calls printMatchingTasks
     * to print the results that match.
     *
     * @param input The text input by user.
     * @return All the matching tasks as a string.
     * @throws DukeException If the user left the search target blank.
     */
    public String handleFind(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("find\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input.trim());

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            // find description is empty
            throw new DukeException("OOPS!!! Field after find cannot be empty :(");
        }

        String target = matcher.group(1); // Extract search target
        return getMatchingTasks(target); // Returns results
    }


    /**
     * Extracts the task type from the user input and calls printSortedTasks
     * to print the specified tasks type in sorted order.
     *
     * @param input The text input by user.
     * @return All the sorted tasks as a string.
     * @throws DukeException If the user left the task type blank or if the task type is invalid.
     */
    public String handleSort(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("sort\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input.trim());

        // Check if the input string matches the pattern
        if (!matcher.matches()) {
            // find description is empty
            throw new DukeException("OOPS!!! Field after sort cannot be empty :(");
        }

        String taskType = matcher.group(1); // Extract task type
        List<? extends Task> sortedTasks = sortTasks(taskType);
        return Ui.getSortedTasks(sortedTasks, taskType);

    }

    /**
     * Sorts the specified type of tasks in the current list by endDate for deadline
     * and startDate for event
     *
     * @param taskType The type of task to be sorted.
     * @return List of sorted tasks.
     * @throws DukeException If the task type is invalid.
     */
    private List<? extends Task> sortTasks(String taskType) throws DukeException {
        if (taskType.equals("deadline")) {
            List<Deadline> deadlines = list.stream()
                    .filter(task -> task instanceof Deadline)
                    .map(task -> (Deadline) task)
                    .sorted()
                    .collect(Collectors.toList());
            return deadlines;
        } else if (taskType.equals("event")) {
            List<Event> events = list.stream()
                    .filter(task -> task instanceof Event)
                    .map(task -> (Event) task)
                    .sorted()
                    .collect(Collectors.toList());
            return events;
        } else {
            throw new DukeException("OOPS!!! Task type for sort is invalid :(");
        }
    }
}
