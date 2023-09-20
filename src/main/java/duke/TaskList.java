package duke;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
     * Checks if the new Task is a duplicate of task
     * already in the task list.
     *
     * @param addTask the new Task to be added.
     * @return True if the new task matches an existing task in the task list.
     */
    public boolean isDuplicate(Task addTask) {
        for (Task task: this.tasks) {
            if (task.isDuplicate(addTask)) {
                return true;
            }
        }
        return false;
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
        if (isDuplicate(newTask)) {
            return (newTask + " is already in the list.\nYou have "
                    + this.tasks.size() + " tasks in the list.");
        }
        this.tasks.add(newTask);
        assert !this.tasks.isEmpty() : "TasksList should not be empty";
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
        String[] inputArray = userInput.split(" ", 2);
        if (inputArray.length != 2 || inputArray[1].isEmpty()) {
            throw new EmptyTaskException(inputArray[0]);
        } else {
            if (inputArray[0].equals("todo")) {
                newTask = new ToDo(inputArray[1]);
            } else if (inputArray[0].equals("deadline")) {
                String[] a = inputArray[1].split(" /by ");
                if (a.length != 2 || a[1].isEmpty()) {
                    throw new EmptyDateException(inputArray[0]);
                }
                newTask = new Deadline(a[0], getDate(a[1]));
            } else {
                assert inputArray[0].equals("event") : "The userInput should start with event";
                // check if contains both the order is correct
                if (userInput.contains("/to") && userInput.indexOf("/from") > userInput.indexOf("/to")) {
                    throw new DukeException("OOPS!! Please follow the format:\nevent DESCRIPTION /from FROM /to TO");
                }

                String[] a = inputArray[1].split(" /from ");
                if (a.length != 2 || a[1].isEmpty()) {
                    throw new EmptyDateException(inputArray[0]);
                }
                String[] fromto = a[1].split(" /to ");
                if (fromto.length != 2 || fromto[1].isEmpty()) {
                    throw new NoEndDateException();
                }
                newTask = new Event(a[0], getDate(fromto[0]), getDate(fromto[1]));
            }
        }

        return newTask;
    }

    /**
     * Change the input date and time to the correct format.
     *
     * @param inputDate The user's input date and time.
     * @return The correct date and time format.
     * @throws DukeException If there's an issue parsing the input date and time.
     */
    public String getDate(String inputDate) throws DukeException {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
            LocalDateTime dateTime = LocalDateTime.parse(inputDate, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("O0PS!! Please ensure your date and "
                    + "time are in the right format (yyyy-MM-dd HHmm).");
        }
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
            assert task.getStatusIcon().equals("X") : "Task should return a Marked Indicator";
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
            assert task.getStatusIcon().equals(" ") : "Task should return a empty String";
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

    /**
     * Returns a string representation of the task list in file format.
     *
     * @return The string representation of the task list in file format.
     */
    public String toFileFormat() {
        String taskList = "";
        for (int j = 0; j < this.tasks.size(); j++) {
            taskList += (this.tasks.get(j).toFileFormat() + "\n");
        }
        return taskList;
    }
}
