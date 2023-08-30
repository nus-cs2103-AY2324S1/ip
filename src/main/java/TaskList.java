import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {

    public static ArrayList<Task> list = new ArrayList<>();
    public enum MarkStatus {
        MARK, UNMARK
    }
    public enum TaskType {
        TODO, EVENT, DEADLINE
    }

    public static void printList() {
        System.out.println(Ui.line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i ++ ) {
            System.out.println("Task " + (i + 1)+ ": " + list.get(i));
        }
        System.out.println(Ui.line);
    }

    public static void toggleMark(MarkStatus mark, String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < list.size()) {
                if (mark == MarkStatus.UNMARK) {
                    list.get(taskIndex).markAsNotDone();
                    System.out.println(Ui.line);
                    System.out.println("OK, I have marked this as undone:");
                    System.out.println("  " + list.get(taskIndex));
                    System.out.println(Ui.line);
                } else if (mark == MarkStatus.MARK){
                    list.get(taskIndex).markAsDone();
                    System.out.println(Ui.line);
                    System.out.println("Good job! I have marked this task as completed:");
                    System.out.println("  " + list.get(taskIndex));
                    System.out.println(Ui.line);
                }
            } else {
                System.out.println("Sorry! you have input an invalid task!");
            }
        }
    }

    public static void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < TaskList.list.size()) {
            Task removedTask = list.remove(taskIndex);
//            Duke.number--;
            System.out.println(Ui.line);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println(Ui.line);
        } else {
            System.out.println("Sorry! You have entered an invalid task number.");
        }
    }

    public static void addTask(TaskType taskType, String description, String userInput, String marked) throws DukeException{
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

    public static void printTasksByDate(String date) {
        LocalDate dueDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedStartDate = LocalDate.parse(date, formatter);
            dueDate = parsedStartDate;
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
                    System.out.println(count + ": " + task.toString());
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String startString = event.getStartString();
                LocalDate startDate = event.getStartDate();
                if  ((startDate != null && startDate.equals(dueDate)) || (startString != null && startString.equals(dueDateString))) {
                    System.out.println(count + ": " + task.toString());
                }
            }
            count++;
        }
    }
}
