import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "      ____________________________________________________________\n";
    public static void greet() {
        System.out.println(horizontalLine
                            + "      " + "Hello! I'm Glenda!\n"
                            + "      " + "What can I do for you?\n"
                            + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine
                            + "      " + "Bye. Hope to see you again soon!\n"
                            + horizontalLine);
    }

    public static void printCommand(String command) {
        System.out.println(horizontalLine
                            + "      " + "added: " + command + "\n"
                            + horizontalLine);
    }

    public static void printTasks(ArrayList tasks) {
        System.out.print(horizontalLine);

        if (tasks.isEmpty()) {
            // Case where there is no tasks to be displayed
            System.out.println("      " + "No tasks added. ");
        } else {
            System.out.println("      " + "Here are the tasks in your list: ");

            for (Object taskObj: tasks) {
                if (taskObj instanceof Task) {
                    Task task = (Task) taskObj;
                    System.out.println("      " + (tasks.indexOf(task) + 1) + ". " + task.getTask());
                }
            }
        }
        System.out.println(horizontalLine);
    }

    public static void markTaskAsDone(Task task) {
        System.out.print(horizontalLine);
        System.out.println("      " + "Great! I've completed this task!");
        task.markAsDone();
        System.out.println("      " + task.getTask());
        System.out.println(horizontalLine);
    }

    public static void markTaskAsUnDone(Task task) {
        System.out.print(horizontalLine);
        System.out.println("      " + "Okay, I have not yet completed this task.");
        task.markAsUndone();
        System.out.println("      " + task.getTask());
        System.out.println(horizontalLine);
    }

    public static void invalidCommand() {
        System.out.println(horizontalLine
                            + "      " + "Invalid command!\n"
                            + horizontalLine);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while(true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                exit();
                return;

            } else if (command.equals("list")) {
                // List out all the tasks added
                printTasks(tasks);

            } else if (command.contains(" ") && command.split(" ", 2)[0].equals("mark")) {
                // Mark tasks as done
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);

                if (taskNumber > tasks.size()) {
                    invalidCommand();
                } else {
                    markTaskAsDone(tasks.get(taskNumber - 1));
                }

            } else if (command.contains(" ") && command.split(" ", 2)[0].equals("unmark")) {
                // Mark tasks as undone
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);

                if (taskNumber > tasks.size()) {
                    invalidCommand();
                } else {
                    markTaskAsUnDone(tasks.get(taskNumber - 1));
                }

            } else {
                // Add task into task list
                printCommand(command);
                tasks.add(new Task(command));
            }
        }
    }
}
