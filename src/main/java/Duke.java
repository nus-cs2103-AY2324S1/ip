import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String HORIZONTAL_LINE = "        ____________________________________________________________\n";
    public static final String FILE_PATH = "data/duke.txt";
    public static final String INDENT = "        ";
    public static void greet() {
        System.out.println(HORIZONTAL_LINE
                            + INDENT + "Hello! I'm Glenda!\n"
                            + INDENT + "What can I do for you?\n"
                            + HORIZONTAL_LINE);
    }
    public static void exit() {
        System.out.println(HORIZONTAL_LINE
                            + INDENT + "Bye. Hope to see you again soon!\n"
                            + HORIZONTAL_LINE);
    }

    public static void printCommand(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Got it. I've added this task to the list:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            // Case where there is no tasks to be displayed
            System.out.println(INDENT + "No tasks added. ");
        } else {
            System.out.println(INDENT + "Here are the task(s) in your list:");

            for (Task task: tasks) {
                System.out.println(INDENT + (tasks.indexOf(task) + 1) + ". " + task.toString());
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTaskAsDone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Great! I've completed this task!");
        task.markAsDone();
        System.out.println(INDENT + task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTaskAsUnDone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I have not yet completed this task:");
        task.markAsUndone();
        System.out.println(INDENT + task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void deleteTask(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I've removed this task:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void invalidCommand(String errorMessage) {
        System.out.println(HORIZONTAL_LINE
                            + INDENT + errorMessage + "\n"
                            + HORIZONTAL_LINE);
    }

    public static void validateTask(String taskDescription, int numberOfTasks) throws DukeException {
        if (taskDescription.contains("event") && !taskDescription.matches("event .*/from .* /to .*")) {
            // Validate event task format
            throw new DukeException("OOPS!!! The format of an event task is " +
                    "\"event TASK_DESCRIPTION /from START /to END\"");

        } else if (taskDescription.contains("deadline") &&
                    !taskDescription.matches("deadline .*/by \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            // Validate deadline task format
            throw new DukeException("OOPS!!! The format of a deadline task is " +
                    "\"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\"");

        } else if (taskDescription.contains("todo") && !taskDescription.matches("todo .*")) {
            // Validate to do task format
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");

        } else if ((taskDescription.contains("mark") && taskDescription.matches("mark \\d+")) ||
                    (taskDescription.contains("unmark") && taskDescription.matches("unmark \\d+")) ||
                    (taskDescription.contains("delete") && taskDescription.matches("delete \\d+"))) {
            // Validate mark task format
            int taskNumber = Integer.parseInt(taskDescription.split(" ")[1]);
            if (taskNumber > numberOfTasks) {
                throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
            }
        }
    }

    private static void saveFileToDisk(ArrayList<Task> tasks) throws IOException {
        File f = new File(FILE_PATH);

        if (!f.exists()) {
            try {
                // Create folder and file
                boolean isDirectoryCreated = f.getParentFile().mkdir();
                boolean isFileCreated = f.createNewFile();
            } catch (FileNotFoundException e) {
                System.out.println(INDENT + "Something went wrong: " + e.getMessage());
            }
        }

        FileWriter fw = new FileWriter(FILE_PATH);
        try {
            // Write tasks into hard disk
            for (Task task: tasks) {
                fw.write(task.fileDescription());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(INDENT + "Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

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
                    validateTask(command, tasks.size());

                    String[] splitCommand = command.split(" ", 2);
                    if (splitCommand[0].equals("event")) {
                        // Add event task into task list
                        String[] taskParts = splitCommand[1].split("/");
                        tasks.add(new Event(taskParts[0], taskParts[1], taskParts[2]));
                        printCommand(tasks.get(tasks.size() - 1), tasks.size());

                    } else if (splitCommand[0].equals("deadline")) {
                        // Add deadline task into task list
                        String[] taskParts = splitCommand[1].split(" /by ");
                        String[] dateAndTime = taskParts[1].split(" ");
                        tasks.add(new Deadline(taskParts[0],
                                                dateAndTime[0],
                                                dateAndTime[1]));
                        printCommand(tasks.get(tasks.size() - 1), tasks.size());

                    } else if (splitCommand[0].equals("todo")) {
                        // Add to-do task into task list
                        tasks.add(new ToDo(command.split(" ", 2)[1]));
                        printCommand(tasks.get(tasks.size() - 1), tasks.size());

                    } else if (splitCommand[0].equals("mark")) {
                        // Mark tasks as done
                        int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                        markTaskAsDone(tasks.get(taskNumber - 1));

                    } else if (splitCommand[0].equals("unmark")) {
                        // Mark tasks as undone
                        int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                        markTaskAsUnDone(tasks.get(taskNumber - 1));

                    } else if (splitCommand[0].equals("delete")) {
                        // Delete task from list
                        int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                        deleteTask(tasks.get(taskNumber - 1), tasks.size() - 1);
                        tasks.remove(taskNumber - 1);

                    } else {
                        // Non-existent task functions
                        throw new DukeException();
                    }
                    saveFileToDisk(tasks);

                } catch (DukeException error) {
                    invalidCommand(error.getMessage());

                } catch (Exception error) {
                    invalidCommand(error.getMessage());
                }
            }
        }


    }
}
