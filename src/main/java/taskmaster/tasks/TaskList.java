package taskmaster.tasks;

import taskmaster.duplicatecheckers.DuplicateDeadlineChecker;
import taskmaster.duplicatecheckers.DuplicateEventChecker;
import taskmaster.duplicatecheckers.DuplicateTodoChecker;
import taskmaster.ui.Ui;
import taskmaster.exceptions.DukeException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    /**
     * List that contains all the current tasks.
     */
    public static ArrayList<Task> list;
    /**
     * Enum that indicates whether a task is marked or unmarked.
     */
    public enum MarkStatus {
        /**
         * Task is marked
         */
        MARK,
        /**
         * Task is unmarked
         */
        UNMARK
    }
    /**
     * Enum that indicates the task type.
     */
    public enum TaskType {
        /**
         * Todo task.
         */
        TODO,
        /**
         * Event task.
         */
        EVENT,
        /**
         * Deadline task.
         */
        DEADLINE
    }
    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        TaskList.list = new ArrayList<>();
    }
    /**
     * Prints all tasks in the list.
     */
    public static String printList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:").append("\n");

        if (list.size() == 0) {
            stringBuilder.append("You do not have any task at the moment :)");
        }

        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append("Task ").append(i + 1).append(": ").append(list.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Marks or un-marks a task to in the list as done.
     *
     * @param mark Indication of whether a task should be marked or unmarked.
     * @param taskIndex of the task to toggle the mark.
     * @throws DukeException If the provided index is out of range.
     */
    public String toggleMark(MarkStatus mark, int taskIndex) throws DukeException {
        //Assert valid task index and mark status
        assert taskIndex >= 0 || taskIndex < list.size() : "Invalid task index";
        assert mark == MarkStatus.MARK || mark == MarkStatus.UNMARK : "Invalid mark status";

        StringBuilder stringBuilder = new StringBuilder();
        if (!validIndex(taskIndex)) {
            throw new DukeException("Invalid task number");
        }

        if (mark == MarkStatus.UNMARK) {
            list.get(taskIndex).markAsNotDone();
            stringBuilder.append("OK, I have marked this as undone:").append("\n");
            stringBuilder.append("  ").append(list.get(taskIndex)).append("\n");
        } else if (mark == MarkStatus.MARK) {
            list.get(taskIndex).markAsDone();
            stringBuilder.append("Good job! I have marked this task as completed:").append("\n");
            stringBuilder.append("  ").append(list.get(taskIndex)).append("\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Removes a task to the list.
     *
     * @param taskIndex Index of the task to remove.
     * @throws DukeException If the provided index is out of range.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        //Assert valid task index and mark status
        assert taskIndex >= 0 || taskIndex < list.size() : "Invalid task index";

        StringBuilder stringBuilder = new StringBuilder();
        if (!validIndex(taskIndex)) {
            throw new DukeException("Specified task does not exist");
        }

        Task removedTask = list.remove(taskIndex);
        stringBuilder.append("Noted. I've removed this task:").append("\n");
        stringBuilder.append("  ").append(removedTask).append("\n");
        stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        return stringBuilder.toString();
    }
    /**
     * Returns a string that will be printed out in the dialogue box.
     *
     * @param taskType Type of task that is added.
     * @param description Description of the task.
     * @param marked Whether the task should be added as marked or unmarked.
     * @return A string which will be formed as a dialogue bubble
     * @throws DukeException If command given is not a valid command.
     */
    public String addTask(TaskType taskType, String description, String marked) throws DukeException {
        //Assert valid task type
        assert taskType == TaskType.TODO || taskType == TaskType.EVENT || taskType == TaskType.DEADLINE: "Invalid task type";
        assert description != null: "Invalid description";

        StringBuilder stringBuilder = new StringBuilder();
        if (taskType == TaskType.TODO) {
            return addTodoTask(description, marked);
        } else if (taskType == TaskType.EVENT) {
            return addEventTask(description, marked);
        } else if (taskType == TaskType.DEADLINE) {
            return addDeadlineTask(description, marked);
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of Taskmaster's reply when a todo is added.
     *
     * @param description Description of the todo.
     * @param marked Whether the task should be added as a marked task.
     * @return A string of Taskmaster's reply when a todo is added.
     * @throws DukeException If description is empty
     */
    public String addTodoTask(String description, String marked) throws DukeException{
        StringBuilder stringBuilder = new StringBuilder();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        //Duplicate checking
        DuplicateTodoChecker todoChecker = new DuplicateTodoChecker();
        if (todoChecker.isDuplicateTodo(description)) {
            stringBuilder.append("Duplicate todo task detected!").append("\n");
            return stringBuilder.toString();
        }
        //No duplicate, add todo to list
        Todo todo = new Todo(description, marked);
        list.add(todo);
        stringBuilder.append("Got it. I've added this to-do task:").append("\n");
        stringBuilder.append("  ").append(todo).append("\n");
        stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of Taskmaster's reply when a deadline is added.
     *
     * @param description Description of the deadline.
     * @param marked Whether the task should be added as a marked task.
     * @return A string of Taskmaster's reply when a deadline is added.
     * @throws DukeException If description is empty or command is invalid
     */
    public String addDeadlineTask(String description, String marked) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] parts = description.split("/by");
        if (parts.length != 2) {
            throw new DukeException("Please input a valid task");
        }

        //Valid description and time checking
        String details = parts[0].trim();
        String by = parts[1].trim();
        if (details.isEmpty() || by.isEmpty()) {
            throw new DukeException("Please input a valid task");
        }

        //Duplicate checking
        DuplicateDeadlineChecker deadlineChecker = new DuplicateDeadlineChecker();
        if (deadlineChecker.isDuplicateDeadline(details, by)) {
            stringBuilder.append("Duplicate deadline task detected").append("\n");
            return stringBuilder.toString();
        }

        //No duplicates, add deadline to list
        list.add(new Deadline(details, by, marked));
        stringBuilder.append("Got it. I've added this deadline:").append("\n");
        stringBuilder.append("  ").append(list.get(list.size() - 1)).append("\n");
        stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        return stringBuilder.toString();
    }

    /**
     * Returns a string representation of Taskmaster's reply when an event is added.
     *
     * @param description Description of the event.
     * @param marked Whether the task should be added as a marked task.
     * @return A string of Taskmaster's reply when an event is added.
     * @throws DukeException If description is empty or command is invalid.
     */
    public String addEventTask(String description, String marked) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        String[] parts = description.split("/from");
        if (parts.length != 2) {
            throw new DukeException("Please input a valid task");
        }

        //Valid description and time checking
        String details = parts[0].trim();
        String[] timeParts = parts[1].split("/to");
        String start = timeParts[0].trim();
        String end = timeParts[1].trim();
        if (timeParts.length != 2 || details.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("Please input a valid task");
        }

        //Duplicate checking
        DuplicateEventChecker eventChecker = new DuplicateEventChecker();
        if (eventChecker.isDuplicateEvent(details, start)) {
            stringBuilder.append("Duplicate event task detected!").append("\n");
            return stringBuilder.toString();
        }
        //No duplicate, add event to list
        list.add(new Event(details, start, end, marked));
        stringBuilder.append("Got it. I've added this event:").append("\n");
        stringBuilder.append("  ").append(list.get(list.size() - 1)).append("\n");
        stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        return stringBuilder.toString();
    }
    /**
     * Prints all tasks that is happening on the specified date.
     *
     * @param date Date that task is happening on.
     */
    public String printTasksByDate(String date) {
        LocalDate dueDate = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dueDate = LocalDate.parse(date, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            stringBuilder.append("Please input a valid date format: yyyy-MM-dd!");
            return stringBuilder.toString();
        }

        stringBuilder.append("Tasks occurring on ").append(dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))).append(":").append("\n");
        int count = 1;

        for (Task task : list) {
            String dueDateString = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String deadlineString = deadline.getStringDate();
                LocalDate deadlineDate = deadline.getLocalDate();
                appendTaskIfMatch(stringBuilder, deadlineString,deadlineDate, dueDateString, dueDate, count, task);
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String startString = event.getStartString();
                LocalDate startDate = event.getStartDate();
                appendTaskIfMatch(stringBuilder, startString, startDate, dueDateString, dueDate, count, task);
            }
            count++;
        }
        return stringBuilder.toString();
    }

    /**
     * Appends a string representation to the Stringbuilder if the specific task is matched with date given.
     *
     * @param sb Stringbuilder object
     * @param taskStringDate String representation of the date of the task.
     * @param taskDate LocalDate representation of the date of the task.
     * @param dueStringDate String representation of the due date provided.
     * @param dueDate LocalDate representation of the due date provided.
     * @param count Count of number of matched tasks.
     * @param task Task that is found in task list.
     */
    private static void appendTaskIfMatch(StringBuilder sb, String taskStringDate, LocalDate taskDate, String dueStringDate, LocalDate dueDate, int count, Task task) {
        if ((taskDate != null && taskDate.equals(dueDate)) || (taskStringDate != null && taskStringDate.equals(dueStringDate))) {
            sb.append(count).append(": ").append(task).append("\n");
        }
    }

    /**
     * String representation of task list.
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return (result + String.format("You have %d %s in the list.",
                list.size(),
                list.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Returns tasks that is matching keyword provided.
     * @param keyword Keyword to match task with.
     */
    public String findTask(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;
        for (Task task: list) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                stringBuilder.append(count).append(": ").append(task).append("\n");

                count++;
            }
        }
        if (count == 1) {
            stringBuilder.append("No task found matching keyword.");
        }
        return stringBuilder.toString();
    }

    /**
     * Check if given index is within size of task list.
     * @param index Index to be checked.
     * @return Boolean to indicate if index is valid.
     */
    private static boolean validIndex(int index) {
        return index >= 0 && index < TaskList.list.size();
    }

    /**
     * Return size of list.
     * @return size of list as integer number.
     */
    public int size() {
        return list.size();
    }
}
