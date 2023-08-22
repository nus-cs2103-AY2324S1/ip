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

    public static void invalidCommand(String errorMessage) {
        System.out.println(horizontalLine
                            + "        " + errorMessage + "\n"
                            + horizontalLine);
    }

    public static void validateTask(String taskDescription, int numberOfTasks) throws DukeException {
        if (taskDescription.contains("event") && !taskDescription.matches("event .*/from .* /to .*")) {
            // Validate event task format
            throw new DukeException("☹ OOPS!!! The format of an event task is " +
                    "event TASK_DESCRIPTION /from START /to END");

        } else if (taskDescription.contains("deadline") && !taskDescription.matches("deadline .*/by .*")) {
            // Validate deadline task format
            throw new DukeException("☹ OOPS!!! The format of a deadline task is " +
                    "deadline TASK_DESCRIPTION /by DEADLINE");

        } else if (taskDescription.contains("todo") && !taskDescription.matches("todo .*")) {
            // Validate todo task format
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");

        } else if ((taskDescription.contains("mark") && taskDescription.matches("mark \\d+")) ||
                    (taskDescription.contains("unmark") && taskDescription.matches("unmark \\d+"))) {
            // Validate mark task format
            int taskNumber = Integer.parseInt(taskDescription.split(" ")[1]);
            if (taskNumber > numberOfTasks) {
                throw new DukeException("☹ OOPS!!! Task " + taskNumber + " does not exist.");
            }
        }
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

            } else {
                try {
                    validateTask(command, numberOfTasks);

                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand[0].equals("event")) {
                        // Add event task into task list
                        String[] taskParts = splitCommand[1].split("/");
                        tasks[numberOfTasks] = new Event(taskParts[0], taskParts[1], taskParts[2]);
                        printCommand(tasks[numberOfTasks++], numberOfTasks);

                    } else if (splitCommand[0].equals("deadline")) {
                        // Add deadline task into tasklist
                        String[] taskParts = splitCommand[1].split(" /by ");
                        tasks[numberOfTasks] = new Deadline(taskParts[0], taskParts[1]);
                        printCommand(tasks[numberOfTasks++], numberOfTasks);

                    } else if (splitCommand[0].equals("todo")) {
                        // Add to-do task into tasklist
                        tasks[numberOfTasks] = new ToDo(command.split(" ", 2)[1]);
                        printCommand(tasks[numberOfTasks++], numberOfTasks);

                    } else if (splitCommand[0].equals("mark")) {
                        // Mark tasks as done
                        int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                        markTaskAsDone(tasks[taskNumber - 1]);

                    } else if (splitCommand[0].equals("unmark")) {
                        // Mark tasks as undone
                        int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                        markTaskAsUnDone(tasks[taskNumber - 1]);

                    } else {
                        // Non-existent task functions
                        throw new DukeException();
                    }

                } catch (DukeException error) {
                    invalidCommand(error.getMessage());

                } catch (Exception error) {
                    invalidCommand(error.getMessage());
                }
            }
        }
    }
}
