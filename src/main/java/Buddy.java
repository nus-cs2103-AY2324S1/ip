import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "Task Buddy";

    public static void main(String[] args) {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        String command;
        Task t;
        List<Task> taskList = new ArrayList();

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(exit);
                break;
            }
            if (command.equals("list")) {
                printList(taskList);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                String[] arrOfCmd = command.split(" ");
                Integer taskNumber = Integer.valueOf(arrOfCmd[1]) - 1;
                if (taskNumber < 0 || taskNumber > taskList.size()) {
                    System.out.println("Invalid task number.");
                } else {
                    Task thisTask = taskList.get(taskNumber);
                    if (command.startsWith("mark")) {
                        thisTask.markTaskAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    }
                    if (command.startsWith("unmark")) {
                        thisTask.markTaskAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println(thisTask.toString());
                }


            } else if (command.startsWith("todo")) {
                String description = command
                        .replaceFirst("todo ", "");
                Todo todo = new Todo(description);
                // t = new Task(description);
                taskList.add(todo);
                System.out.println("Got it. I've added this task:\n" + todo.toString());
                if (taskList.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }

            } else if (command.startsWith("deadline")) {
                String[] deadlineArr = command
                        .replaceFirst("deadline ", "")
                        .split("/", 2);
                String description = deadlineArr[0];
                String deadlineBy = deadlineArr[1].replaceFirst("by ", "").trim();
                Deadline deadline = new Deadline(description, deadlineBy);
                // t = new Task(description);
                taskList.add(deadline);
                System.out.println("Got it. I've added this task:\n"
                        + deadline.toString());
                if (taskList.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }

            } else if (command.startsWith("event")) {
                String[] eventArr = command
                        .replaceFirst("event ", "")
                        .split("/", 3);
                String description = eventArr[0];
                String eventStart = eventArr[1].replaceFirst("from ", "").trim();
                String eventEnd = eventArr[2].replaceFirst("to ", "").trim();
                Event event = new Event(description, eventStart, eventEnd);
                // t = new Task(description);
                taskList.add(event);
                System.out.println("Got it. I've added this task:\n" + event.toString());
                if (taskList.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            }

            else {
                t = new Task(command);
                taskList.add(t);
                System.out.println("Got it. I've added this task:\n" + command);
                if (taskList.size() == 1) {
                    System.out.println("Now you have 1 task in the list.");
                } else {
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                }
            }
        }
    }
    private static void printList(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("There are no tasks in your list:");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + "." + task.toString());
            }
        }
    }
}
