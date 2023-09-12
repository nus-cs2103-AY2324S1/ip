package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidRangeException;
import duke.exception.NoEndDateException;

/**
 * Tasklist is a class in-charge of task list.
 * It has method to add, delete, unmark and mark tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new empty duke.TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new duke.TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the duke.TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param userInput The user's input.
     * @return String message to be printed
     * @throws DukeException If userInput does not meet task requirements.
     */
    public String addTask(String userInput) throws DukeException {
        Task newTask = createNewTask(userInput);
        this.tasks.add(newTask);
        return ("Got it. I've added this task:\n " + newTask
                + "\nNow you have " + this.tasks.size() + " tasks in the list.");

    }

    /**
     * Creates a new task based on the user's input.
     *
     * @param userInput The user's input.
     * @return A new duke.Task object.
     * @throws DukeException If userInput does not meet task requirements.
     */
    public Task createNewTask(String userInput) throws DukeException {
        Task newTask;
        String[] arr = userInput.split(" ", 2);

        if (arr.length != 2 || arr[1].isEmpty()) {
            throw new EmptyTaskException(arr[0]);
        }
        if (arr[0].equals("todo")) {
            newTask = new ToDo(arr[1]);
        } else if (arr[0].equals("deadline")) {
            String[] a = arr[1].split(" /by ");
            if (a.length != 2 || a[1].isEmpty()) {
                throw new EmptyDateException(arr[0]);
            }
            newTask = new Deadline(a[0], getDate(a[1]));
        } else {
            String[] a = arr[1].split(" /from ");
            if (a.length != 2 || a[1].isEmpty()) {
                throw new EmptyDateException(arr[0]);
            }

            String[] fromto = a[1].split("/to ");
            if (fromto.length != 2 || fromto[1].isEmpty()) {
                throw new NoEndDateException();
            }
            newTask = new Event(a[0], getDate(fromto[0]), getTime(fromto[1]));
        }

        return newTask;
    }

    /**
     * Change the input date and time to the correct format.
     *
     * @param inputDate The user's input date.
     * @return The correct date and time format.
     */
    public String getDate(String inputDate) {
        String[] dateTime = inputDate.split(" ");
        if (!dateTime[0].isEmpty()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(
                    "[M/d/yyyy][MM/dd/yyyy][yyyy-MM-dd]");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            if (dateTime[0].contains("-") || dateTime[0].contains("/")) {
                LocalDate date = LocalDate.parse(dateTime[0], inputFormatter);
                dateTime[0] = date.format(outputFormatter);
                inputDate = dateTime[0];
            }
        }

        // Time
        if (dateTime.length == 2 && !dateTime[1].isEmpty()) {
            inputDate = dateTime[0] + " " + getTime(dateTime[1]);
        }

        return inputDate;
    }

    /**
     * Change the input time to the correct format.
     *
     * @param time The user's input time.
     * @return The correct time format.
     */
    public String getTime(String time) {

        // check if is integer
        if (time.matches("^-?\\d+$")) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("ha");
            LocalTime outputTime = LocalTime.parse(time, inputFormatter);
            time = outputTime.format(outputFormatter);
        }

        return time;
    }

    /**
     * Deletes the desired task from the task list.
     *
     * @param taskIndex The index of the desired deleted task.
     * @return A message confirming the deletion of the task.
     * @throws InvalidRangeException If the task index is exceeds the range.
     */
    public String deleteTask(int taskIndex) throws InvalidRangeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task deletedTask = this.tasks.remove(taskIndex);
            return ("Noted. I've removed this task:\n " + deletedTask
                    + "\nNow you have " + this.tasks.size() + " tasks in the list.");
        } else {
            throw new InvalidRangeException("Invalid task index. You have "
                    + this.tasks.size() + " tasks in the list.");
        }

    }

    /**
     * Unmarks the desired task as done.
     *
     * @param taskIndex The index of the task to mark as done.
     * @return A message confirming the task as done.
     * @throws InvalidRangeException If the task index is exceeds the range.
     */
    public String markTaskAsDone(int taskIndex) throws InvalidRangeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task task = this.tasks.get(taskIndex);
            task.markAsDone();
            return ("Nice! I've marked this task as done:\n " + task);
        } else {
            throw new InvalidRangeException("Invalid task index. You have "
                    + this.tasks.size() + " tasks in the list.");
        }

    }

    /**
     * Marks the desired task as undone.
     *
     * @param taskIndex The index of the task to mark as undone.
     * @return A message confirming the task as undone.
     * @throws InvalidRangeException If the task index is exceeds the range.
     */
    public String unmarkTaskAsDone(int taskIndex) throws InvalidRangeException {
        if (taskIndex >= 0 && taskIndex < this.tasks.size()) {
            Task task = this.tasks.get(taskIndex);
            task.markAsUndone();
            return ("OK, I've marked this task as not done yet:\n " + task);
        } else {
            throw new InvalidRangeException("Invalid task index. You have "
                    + this.tasks.size() + " tasks in the list.");
        }

    }

    /**
     * Finds tasks that contains a given keyword.
     *
     * @param keyword The keyword to search for.
     * @return The string representation of the matching task list.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>(
                tasks.stream()
                        .filter(task -> task.getDescription().contains(keyword))
                        .collect(Collectors.toList()));

        // String representation of matching task
        String taskList = "Here are the matching tasks in your list:";
        for (int j = 0; j < matchingTasks.size(); j++) {
            int index = j + 1;
            taskList += ("\n" + index + "." + matchingTasks.get(j).toString());
        }

        return taskList;
    }



    /**
     * Returns a string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        String taskList = "Here are the tasks in your list:";
        for (int j = 0; j < this.tasks.size(); j++) {
            int index = j + 1;
            taskList += ("\n" + index + "." + this.tasks.get(j).toString());
        }

        return taskList;
    }
}
