import java.util.Scanner;

public class Dukduk {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();

        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(" ");
                String input = scanner.nextLine();
                printLine();

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    printLine();
                    scanner.close();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    if (taskCount == 0) {
                        System.out.println(" No tasks added yet.");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + "." + tasks[i]);
                        }
                    }
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new DukdukException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks[taskCount] = new ToDo(input.substring(5));
                    taskCount++;
                    System.out.println(" Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new DukdukException("OOPS!!! The deadline format is incorrect. Use '/by' to specify the deadline.");
                    }
                    String description = input.substring(9, byIndex).trim();
                    String by = input.substring(byIndex + 3).trim();
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new DukdukException("OOPS!!! The event format is incorrect. Use '/from' and '/to' to specify the timings.");
                    }
                    String description = input.substring(6, fromIndex).trim();
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;
                    System.out.println(" Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].markAsDone();
                        System.out.println(" Nice! I've marked this task as done:\n ["
                                + tasks[taskIndex].getStatusIcon() + "] "
                                + tasks[taskIndex].getDescription());
                    }
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskCount) {
                        tasks[taskIndex].unmark();
                        System.out.println(" OK, I've marked this task as not done yet:\n ["
                                + tasks[taskIndex].getStatusIcon() + "] "
                                + tasks[taskIndex].getDescription());
                    }
                } else {
                    throw new DukdukException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                printLine();
            } catch (DukdukException e) {
                System.out.println(" ☹ " + e.getMessage());
                printLine();
            }
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
