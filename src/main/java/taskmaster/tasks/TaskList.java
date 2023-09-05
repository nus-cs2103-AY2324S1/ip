package taskmaster.tasks;

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
        StringBuilder stringBuilder = new StringBuilder();
        if (taskIndex >= 0 && taskIndex < TaskList.list.size()) {
            if (mark == MarkStatus.UNMARK) {
                list.get(taskIndex).markAsNotDone();
                stringBuilder.append("OK, I have marked this as undone:").append("\n");
                stringBuilder.append("  ").append(list.get(taskIndex)).append("\n");
            } else if (mark == MarkStatus.MARK) {
                list.get(taskIndex).markAsDone();
                stringBuilder.append("Good job! I have marked this task as completed:").append("\n");
                stringBuilder.append("  ").append(list.get(taskIndex)).append("\n");
            }
        } else {
            throw new DukeException("Invalid task number");
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
        StringBuilder stringBuilder = new StringBuilder();
        if (taskIndex >= 0 && taskIndex < TaskList.list.size()) {
            Task removedTask = list.remove(taskIndex);
            stringBuilder.append("Noted. I've removed this task:").append("\n");
            stringBuilder.append("  ").append(removedTask).append("\n");
            stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        } else {
            throw new DukeException("Specified task does not exist");
        }
        return stringBuilder.toString();
    }
    /**
     * Removes a task to the list.
     *
     * @param taskType Type of task that is added.
     * @param description Description of the task.
     * @param marked Whether the task should be added as marked or unmarked.
     * @throws DukeException If command given is not a valid command.
     */
    public String addTask(TaskType taskType, String description, String marked) throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();

        if (taskType == TaskType.TODO) {
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Todo todo = new Todo(description, marked);
            list.add(todo);
            stringBuilder.append("Got it. I've added this to-do task:").append("\n");
            stringBuilder.append("  ").append(todo).append("\n");
            stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
        } else if (taskType == TaskType.EVENT) {
            boolean wrongInput = false;
            String[] parts = description.split("/from");
            if (parts.length == 2) {
                String details = parts[0].trim();
                String[] timeParts = parts[1].split("/to");
                if (timeParts.length == 2) {
                    String from = timeParts[0].trim();
                    String end = timeParts[1].trim();
                    list.add(new Event(details, from, end, marked));
                } else {
                    wrongInput = true;
                }
            } else {
                wrongInput = true;
            }
            if (wrongInput) {
                throw new DukeException("Please input a valid task");
            } else {
                stringBuilder.append("Got it. I've added this to-do task:").append("\n");
                stringBuilder.append("  ").append(list.get(list.size() -1)).append("\n");
                stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
            }
        } else if (taskType == TaskType.DEADLINE) {
            boolean wrongInput = false;
            String[] parts = description.split("/by");
            if (parts.length == 2) {
                String details = parts[0].trim();
                String by = parts[1].trim();
                list.add(new Deadline(details, by, marked));
            } else {
                wrongInput = true;
            }
            if (wrongInput) {
                throw new DukeException("Please input a valid task");
            } else {
                stringBuilder.append("Got it. I've added this to-do task:").append("\n");
                stringBuilder.append("  ").append(list.get(list.size() -1)).append("\n");
                stringBuilder.append("Now you have ").append(list.size()).append(" tasks in the list.").append("\n");
            }
        }
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
                if ((deadlineDate != null && deadlineDate.equals(dueDate)) || (deadlineString != null && deadlineString.equals(dueDateString))) {
                    stringBuilder.append(count).append(": ").append(task).append("\n");
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String startString = event.getStartString();
                LocalDate startDate = event.getStartDate();
                if  ((startDate != null && startDate.equals(dueDate)) || (startString != null && startString.equals(dueDateString))) {
                    stringBuilder.append(count).append(": ").append(task).append("\n");
                }
            }
            count++;
        }
        return stringBuilder.toString();
    }

    /**
     * String representation of task list.
     * @return String representation of task list.
     */
    @Override public String toString() {
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
}
