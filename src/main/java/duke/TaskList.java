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
    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }

    /**
     * Marks a task as completed based on user input.
     *
     * @param input The input string containing the task index to mark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void markTask(String input) throws OutOfIndexException {
        int item = input.charAt(5) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.get(item);
        curr.setMark(true);
        System.out.println("I HAVE MARKED THIS TASK:");
        System.out.println(curr);
    }

    /**
     * Marks a completed task as incomplete based on user input.
     *
     * @param input The input string containing the task index to unmark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void unmarkTask(String input) throws OutOfIndexException {
        int item = input.charAt(5) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.get(item);
        curr.setMark(false);
        System.out.println("I HAVE UNMARKED THIS TASK:");
        System.out.println(curr);
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The input string containing the task index to delete.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void deleteTask(String input) throws OutOfIndexException {
        int item = input.charAt(7) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.remove(item);
        System.out.println("I HAVE DELETED THE FOLLOWING TASK:");
        System.out.println(curr);
        System.out.println("NOW YOU HAVE " + list.size() + " TASKS LEFT");
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
    public void addTask(String input) throws InvalidDateFormatException, EmptyDeadlineException,
            EmptyTodoException, EmptyEventException, MissingByException, MissingFromException,
            MissingToException, MissingTitleException, InvalidInputException {
        Task added = null;
        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new EmptyTodoException();
            }
            String title = input.substring(5);
            added = new Todo(title, false);
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new EmptyDeadlineException();
            }
            int index = input.indexOf("/by");
            if (index == -1) {
                throw new MissingByException();
            }
            if (index < 10) {
                throw new MissingTitleException();
            }
            String title = input.substring(9, index - 1);
            String dueDate = input.substring(index + 4);
            added = new Deadline(title, parseDate(dueDate), false);
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new EmptyEventException();
            }
            int fromIndex = input.indexOf("/from");
            if (fromIndex == -1) {
                throw new MissingFromException();
            }
            if (fromIndex < 7) {
                throw new MissingTitleException();
            }
            String title = input.substring(6, fromIndex - 1);
            int toIndex = input.indexOf("/to");
            if (toIndex == -1) {
                throw new MissingToException();
            }
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            added = new Event(title, parseDate(from), parseDate(to), false);
        }
        if (added != null) {
            list.add(added);
            System.out.println("I'VE ADDED THIS TASK:");
            System.out.println(added);
            System.out.println("YOU HAVE " + list.size() + " TASKS IN THE LIST");
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
    public List<Task> find(String input) throws InvalidFindException {
        if (input.length() < 5) {
            throw new InvalidFindException();
        }
        String keywords = input.substring(5);
        List<Task> filteredTasks = new ArrayList<>();
        for (Task curr : list) {
            if (curr.getTitle().indexOf(keywords) != -1) {
                filteredTasks.add(curr);
            }
        }
        System.out.println("TASKS FOUND:");
        for (int i = 0; i < filteredTasks.size(); i++) {
            System.out.println(i + ". " + filteredTasks.get(i));
        }
        return filteredTasks;
    }
}
