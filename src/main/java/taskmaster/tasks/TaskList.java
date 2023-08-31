package taskmaster.tasks;

import taskmaster.ui.Ui;
import taskmaster.exceptions.DukeException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    public static ArrayList<Task> list;
    public enum MarkStatus {
        MARK, UNMARK
    }
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    public TaskList() {
        TaskList.list = new ArrayList<>();
    }

    public static void printList() {
        System.out.println(Ui.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Task " + (i + 1) + ": " + list.get(i));
        }
        System.out.println(Ui.line);
    }

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
                System.out.println("Please input a valid task");
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
                System.out.println("Please input a valid task");
            } else {
                System.out.println(Ui.line);
                System.out.println("Got it. I've added this deadline:");
                System.out.println("  " + list.get(list.size() - 1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println(Ui.line);
            }
        }
    }

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

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }
        return (result + String.format("You have %d %s in the list.",
                list.size(),
                list.size() == 1 ? "task" : "tasks"));
    }
}
