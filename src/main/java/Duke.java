import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String horizontalLine = "        ____________________________________________________________\n";
    public static void greet() {
        System.out.println(horizontalLine
                            + "        " + "Hello! I'm Glenda!\n"
                            + "        " + "What can I do for you?\n"
                            + horizontalLine);
    }
    public static void exit() {
        System.out.println(horizontalLine
                            + "        " + "Bye. Hope to see you again soon!\n"
                            + horizontalLine);
    }

    public static void printCommand(Task task, int numberOfTasks) {
        System.out.print(horizontalLine);
        System.out.println("        " + "Got it. I've added this task to the list:");
        System.out.println("          " + task.toString());
        System.out.println("        " + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(horizontalLine);
    }

    public static void printTasks(Task[] tasks) {
        System.out.print(horizontalLine);

        if (tasks[0] == null) {
            // Case where there is no tasks to be displayed
            System.out.println("        " + "No tasks added. ");
        } else {
            System.out.println("        " + "Here are the task(s) in your list:");

            int taskNumber = 0;
            while (tasks[taskNumber] != null) {
                System.out.println("        " + (taskNumber + 1) + ". " + tasks[taskNumber].toString());
                taskNumber++;
            }
        }
        System.out.println(horizontalLine);
    }

    public static void markTaskAsDone(Task task) {
        System.out.print(horizontalLine);
        System.out.println("        " + "Great! I've completed this task!");
        task.markAsDone();
        System.out.println("        " + task.toString());
        System.out.println(horizontalLine);
    }

    public static void markTaskAsUnDone(Task task) {
        System.out.print(horizontalLine);
        System.out.println("        " + "Okay, I have not yet completed this task.");
        task.markAsUndone();
        System.out.println("        " + task.toString());
        System.out.println(horizontalLine);
    }

    public static void invalidCommand() {
        System.out.println(horizontalLine
                            + "        " + "Invalid command!\n"
                            + horizontalLine);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        while(true) {
            // Get the next task input
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                // Exit the chatbot
                exit();
                return;

            } else if (command.equals("list")) {
                // List out all the tasks added
                printTasks(tasks);

            } else if (command.contains(" ")) {
                String[] splitCommand = command.split(" ", 2);

                if (command.contains("/")) {

                    if (splitCommand[0].equals("event")) {
                        // Case for event task
                        String[] taskParts = splitCommand[1].split(" /from | /to ");
                        tasks[numberOfTasks] = new Event(taskParts[0], taskParts[1], taskParts[2]);

                    } else if (splitCommand[0].equals("deadline")) {
                        // Case for deadline task
                        String[] taskParts = splitCommand[1].split(" /by ");
                        tasks[numberOfTasks] = new Deadline(taskParts[0], taskParts[1]);
                    }
                    printCommand(tasks[numberOfTasks++], numberOfTasks);

                } else if (splitCommand[0].equals("mark")) {
                    // Mark tasks as done
                    int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);

                    if (tasks[taskNumber - 1] == null) {
                        invalidCommand();
                    } else {
                        markTaskAsDone(tasks[taskNumber - 1]);
                    }

                } else if (splitCommand[0].equals("unmark")) {
                    // Mark tasks as undone
                    int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);

                    if (tasks[taskNumber - 1] == null) {
                        invalidCommand();
                    } else {
                        markTaskAsUnDone(tasks[taskNumber - 1]);
                    }

                } else if (splitCommand[0].equals("todo")) {
                    // Case for toDo task
                    tasks[numberOfTasks] = new ToDo(command.split(" ", 2)[1]);
                    printCommand(tasks[numberOfTasks++], numberOfTasks);
                }
            }
        }
    }
}
