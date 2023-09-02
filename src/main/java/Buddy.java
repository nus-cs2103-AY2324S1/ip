import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "Task Buddy";
    private List<Task> taskList;
    private final String filePath = "./data/tasks.txt";

    public static void main(String[] args) throws BuddyException {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        String exit = "Bye. Hope to see you again soon!\n";

        Scanner scanner = new Scanner(System.in);
        String command;
        TaskList tasks = new TaskList();

        System.out.println(greeting + inquiry);

        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.print(tasks);
            } else if (command.startsWith("mark") || command.startsWith("unmark")
                    || command.startsWith("delete")) {
                String[] arrOfCmd = command.split(" ");
                Integer taskIndex = Integer.valueOf(arrOfCmd[1]) - 1;

                try {
                    // Task thisTask = tasks.getTask(taskIndex);
                    if (command.startsWith("mark")) {
                        tasks.markAsDone(taskIndex);
                        // System.out.println("Nice! I've marked this task as done:");
                    }
                    if (command.startsWith("unmark")) {
                        tasks.markAsNotDone(taskIndex);
                        // System.out.println("OK, I've marked this task as not done yet:");
                    }
                    if (command.startsWith("delete")) {
                        tasks.deleteTask(taskIndex);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number.");
                }

//            } else if (command.startsWith("delete")) {
//                String[] arrOfCmd = command.split(" ");
//                try {
//                    Integer taskIndex = Integer.valueOf(arrOfCmd[1]) - 1;
//                    tasks.deleteTask(taskIndex);
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Invalid task number.");
//                }
//
//            } else if (command.startsWith("todo")) {
//                String description = command
//                        .replaceFirst("todo", "");
//                if (description.equals("")) {
//                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
//                    //throw new BuddyException("OOPS!!! The description of a todo cannot be empty.");
//                } else {
//                    Todo todo = new Todo(description);
//                    // t = new Task(description);
//                    tasks.addTask(todo);
//                    System.out.println("Got it. I've added this task:\n" + todo.toString());
////                    if (tasks == 1) {
////                        System.out.println("Now you have 1 task in the list.");
////                    } else {
//                        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
//                    // }
//                }
//
//            } else if (command.startsWith("deadline")) {
//                String[] deadlineArr = command
//                        .replaceFirst("deadline", "")
//                        .split("/", 2);
//                try {
//                    String description = deadlineArr[0];
//                    String deadlineBy = deadlineArr[1].replaceFirst("by ", "").trim();
//                    Deadline deadline = new Deadline(description, deadlineBy);
//                    // t = new Task(description);
//                    tasks.addTask(deadline);
//                    System.out.println("Got it. I've added this task:\n"
//                            + deadline.toString());
//                    if (tasks.getSize() == 1) {
//                        System.out.println("Now you have 1 task in the list.");
//                    } else {
//                        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
//                    }
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Please include a description and deadline.");
//                }
//
//
//            } else if (command.startsWith("event")) {
//                try {
//                    String[] eventArr = command
//                            .replaceFirst("event", "")
//                            .split("/", 3);
//                    String description = eventArr[0];
//                    String eventStart = eventArr[1].replaceFirst("from ", "").trim();
//                    String eventEnd = eventArr[2].replaceFirst("to ", "").trim();
//                    Event event = new Event(description, eventStart, eventEnd);
//                    // t = new Task(description);
//                    tasks.addTask(event);
//                    System.out.println("Got it. I've added this task:\n" + event.toString());
//                    if (tasks.getSize() == 1) {
//                        System.out.println("Now you have 1 task in the list.");
//                    } else {
//                        System.out.println("Now you have " + tasks.getSize()  + " tasks in the list.");
//                    }
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Please include event description, start and end date or time.");
//                }
//
//            }

            } else {
                tasks.processCommand(command);
            }
        }
    }
}
