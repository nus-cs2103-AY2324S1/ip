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
    public static void printList() {
        System.out.println(Ui.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + list.get(i));
        }
        System.out.println(Ui.line);
    }
    /**
     * Marks or un-marks a task to in the list as done.
     *
     * @param mark Indication of whether a task should be marked or unmarked.
     * @param taskIndex of the task to toggle the mark.
     * @throws DukeException If the provided index is out of range.
     */
    public void toggleMark(MarkStatus mark, int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < TaskList.list.size()) {
            if (mark == MarkStatus.UNMARK) {
                list.get(taskIndex).markAsNotDone();
                System.out.println(Ui.line);
                System.out.println("OK, I have marked this as undone:");
                System.out.println("  " + list.get(taskIndex));
                System.out.println(Ui.line);
            } else if (mark == MarkStatus.MARK) {
                list.get(taskIndex).markAsDone();
                System.out.println(Ui.line);
                System.out.println("Good job! I have marked this task as completed:");
                System.out.println("  " + list.get(taskIndex));
                System.out.println(Ui.line);
            }
        } else {
            throw new DukeException("Invalid task number");
        }
    }
    /**
     * Removes a task to the list.
     *
     * @param taskIndex Index of the task to remove.
     * @throws DukeException If the provided index is out of range.
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex >= 0 && taskIndex < TaskList.list.size()) {
            Task removedTask = list.remove(taskIndex);
            System.out.println(Ui.line);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(Ui.line);
        } else {
            throw new DukeException("Specified task does not exist");
        }
    }
    /**
     * Removes a task to the list.
     *
     * @param taskType Type of task that is added.
     * @param description Description of the task.
     * @param marked Whether the task should be added as marked or unmarked.
     * @throws DukeException If command given is not a valid command.
     */
    public void addTask(TaskType taskType, String description, String marked) throws DukeException {
        if (taskType == TaskType.TODO) {
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            list.add(new Todo(description, marked));
            System.out.println(Ui.line);
            System.out.println("Got it. I've added this to-do task:");
            System.out.println("  " + list.get(list.size() - 1));
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(Ui.line);
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
                System.out.println(Ui.line);
                System.out.println("Got it. I've added this event:");
                System.out.println("  " + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(Ui.line);
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
                System.out.println(Ui.line);
                System.out.println("Got it. I've added this deadline:");
                System.out.println("  " + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(Ui.line);
            }
        }
    }
    /**
     * Prints all tasks that is happening on the specified date.
     *
     * @param date Date that task is happening on.
     */
    public void printTasksByDate(String date) {
        LocalDate dueDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dueDate = LocalDate.parse(date, formatter);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Please input a valid date format: yyyy-mm-dd!");
        }
        System.out.println("Tasks occurring on " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");

        int count = 1;

        for (Task task : list) {
            String dueDateString = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String deadlineString = deadline.getStringDate();
                LocalDate deadlineDate = deadline.getLocalDate();
                if ((deadlineDate != null && deadlineDate.equals(dueDate)) || (deadlineString != null && deadlineString.equals(dueDateString))) {
                    System.out.println(count + ": " + task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String startString = event.getStartString();
                LocalDate startDate = event.getStartDate();
                if  ((startDate != null && startDate.equals(dueDate)) || (startString != null && startString.equals(dueDateString))) {
                    System.out.println(count + ": " + task);
                }
            }
            count++;
        }
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
    public void findTask(String keyword) {
        System.out.println(Ui.line);
        int count = 1;
        for (Task task: list) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                System.out.println(count + ": " + task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No task found with given keywords!");
        }
        System.out.println(Ui.line);
    }
}
