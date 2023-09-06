package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.EmptyDeadlineException;
import duke.exceptions.EmptyEventException;
import duke.exceptions.EmptyTodoException;
import duke.exceptions.InvalidDateFormatException;
import duke.exceptions.InvalidFindException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.MissingByException;
import duke.exceptions.MissingFromException;
import duke.exceptions.MissingTitleException;
import duke.exceptions.MissingToException;
import duke.exceptions.OutOfIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import javafx.beans.InvalidationListener;

/**
 * Represents an archive of tasks in the Duke application.
 * This class provides methods to manage and manipulate tasks.
 */
public class TaskList {
    protected List<Task> list;
    /**
     * Constructs a new TaskList object with the specified list of tasks.
     *
     * @param list The initial list of tasks to be managed.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }


    /**
     * Prints the list of tasks in the archive along with their indices.
     */
    public String getAll() {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += i + ". " + list.get(i) + "\n";
        }
        return res;
    }

    public int getSize() {
        return list.size();
    }
    /**
     * Marks a task as completed based on user input.
     *
     * @param input The input string containing the task index to mark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public String markTask(int index) {
        Task curr = list.get(index);
        curr.setMark(true);
        return "I HAVE MARKED THIS TASK:" + curr;
    }

    /**
     * Marks a completed task as incomplete based on user input.
     *
     * @param input The input string containing the task index to unmark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public String unmarkTask(int index) {
        Task curr = list.get(index);
        curr.setMark(false);
        return "I HAVE UNMARKED THIS TASK:" + curr;
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The input string containing the task index to delete.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public String deleteTask(int index) {
        Task curr = list.remove(index);
        return "I HAVE DELETED THE FOLLOWING TASK:" + curr + "\nNOW YOU HAVE "
                + list.size() + " TASKS LEFT";
    }

    /**
     * Adds a task to the archive based on user input.
     *
     * @param input The input string containing the task details.
     * @throws EmptyDeadlineException If the deadline task is missing required details.
     * @throws EmptyTodoException     If the todo task is missing a title.
     * @throws EmptyEventException    If the event task is missing required details.
     * @throws MissingByException     If the deadline task is missing the "by" date.
     * @throws MissingFromException   If the event task is missing the start date.
     * @throws MissingToException     If the event task is missing the end date.
     * @throws MissingTitleException  If a task is missing its title.
     * @throws InvalidInputException  If the input doesn't match any valid task format.
     */
    public String addTask(String[] input) throws InvalidInputException, InvalidDateFormatException {
        Task added = null;
        if (input[0].equals("todo")) {
            added = new Todo(input[1], false);
        } else if (input[0].equals("deadline")) {
            added = new Deadline(input[1], parseDate(input[2]), false);
        } else if (input[0].equals("event")) {
            added = new Event(input[0], parseDate(input[1]), parseDate(input[2]), false);
        }
        if (added != null) {
            list.add(added);
            return ("I'VE ADDED THIS TASK: " + added + "\nYOU HAVE " + list.size() + " TASKS IN THE LIST");
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * Parses a date-time string into a LocalDateTime instance.
     *
     * @param input The input date-time string.
     * @return A LocalDateTime instance parsed from the input string.
     * @throws InvalidDateFormatException If the input string has an invalid date-time format.
     */
    public LocalDateTime parseDate(String input) throws InvalidDateFormatException {
        try {
            if (input.length() == 10) {
                input += " 12:00";
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Finds tasks in the task list that match the given keyword(s) in their titles.
     *
     * @param input The input string containing the keyword(s) to search for.
     * @return A list of tasks that match the search criteria.
     * @throws InvalidFindException If the provided search input is too short to be valid.
     */
    public String find(String keywords) throws InvalidFindException {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task curr : list) {
            if (curr.getTitle().indexOf(keywords) != -1) {
                filteredTasks.add(curr);
            }
        }
        String res = "TASKS FOUND:\n";
        for (int i = 0; i < filteredTasks.size(); i++) {
            res += i + ". " + filteredTasks.get(i) + "\n";
        }
        return res;
    }
}
