package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    private Stack<List<Task>> previousLists;
    /**
     * Constructs a new TaskList object with the specified list of tasks.
     *
     * @param list The initial list of tasks to be managed.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        this.previousLists = new Stack<>();
    }
    /**
     * Prints the list of tasks in the archive along with their indices.
     */
    public String getAll() {
        this.previousLists.pop();
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += i + ". " + list.get(i) + "\n";
        }
        if (res.length() == 0) {
            res = "You have not added any tasks yet";
        }
        return res;
    }

    /**
     * Saves the curent state of tasks for future undo
     */
    public void pushCurr() {
        List<Task> currTasks = new ArrayList<>();
        for (Task task : this.list) {
            currTasks.add(task.clone());
        }
        previousLists.push(currTasks);
    }

    /**
     * Undoes the most recent change to the task list, reverting it to a previous state.
     *
     * @return A message indicating the result of the undo operation.
     */
    public String undo() {
        if (this.previousLists.isEmpty()) {
            return "Already at the latest change";
        }
        this.previousLists.pop();
        if (this.previousLists.isEmpty()) {
            return "Already at the latest change";
        }
        this.list = previousLists.pop();
        return "Undid 1 change.";
    }

    public int getSize() {
        return list.size();
    }
    /**
     * Marks a task as completed based on user input.
     *
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public String markTask(int index) {
        Task curr = list.get(index);
        curr.setMark(true);
        assert curr.getIsMarked() : "umarking a task does not unmark it";
        return "I HAVE MARKED THIS TASK:" + curr;
    }

    /**
     * Marks a completed task as incomplete based on user input.
     *
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public String unmarkTask(int index) {
        Task curr = list.get(index);
        curr.setMark(false);
        assert !curr.getIsMarked() : "umarking a task does not unmark it";
        return "I HAVE UNMARKED THIS TASK:" + curr;
    }

    /**
     * Deletes a task based on user input.
     *
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
        String taskType = input[0];
        String title = input[1];
        if (taskType.equals("todo")) {
            added = new Todo(title, false);
        } else if (taskType.equals("deadline")) {
            String by = input[2];
            added = new Deadline(title, parseDate(by), false);
        } else if (taskType.equals("event")) {
            String from = input[2];
            String to = input[3];
            added = new Event(title, parseDate(from), parseDate(to), false);
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
            assert input.length() == 16 : "date format is incorrect even after correction";
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
     * @return A list of tasks that match the search criteria.
     * @throws InvalidFindException If the provided search input is too short to be valid.
     */
    public String find(String keywords) throws InvalidFindException {
        this.previousLists.pop();
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
        if (res.length() == 0) {
            res = "No matching tasks found!";
        }
        return res;
    }

}
