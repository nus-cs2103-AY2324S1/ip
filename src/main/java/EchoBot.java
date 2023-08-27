import java.io.*;
import java.time.LocalDate;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EchoBot {
    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }
    public static String extractTaskDesc(String userInput, String command) {
        return userInput.substring(command.length()).trim();
    }

    private static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    private static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



    // Load tasks from the file
    public static ArrayList<Task> loadTasks(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File folder = file.getParentFile();

        // Create the parent folder if it doesn't exist
        if (!folder.exists() && !folder.mkdirs()) {
            System.err.println("Unable to create directory: " + folder.getAbsolutePath());
            return tasks; // Return an empty list
        }

        try {
            if (!file.exists() && !file.createNewFile()) {
                System.err.println("Unable to create file: " + filePath);
                return tasks; // Return an empty list
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String formattedTask = scanner.nextLine();
                try {
                    Task task = Task.fromFileString(formattedTask);
                    tasks.add(task);
                } catch (NumberFormatException e) {
                    // Handle corrupted data - logging the issue
                    Logger logger = Logger.getLogger(EchoBot.class.getName());
                    logger.log(Level.SEVERE, "Corrupted data: " + formattedTask, e);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid data: " + formattedTask);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("An error occurred while handling file operations: " + e.getMessage());
        } catch (Exception e) {
            Logger logger = Logger.getLogger(EchoBot.class.getName());
            logger.log(Level.SEVERE, "An error occurred while loading tasks", e);
        }

        return tasks;
    }

    // Save tasks to the file
    public static void saveTasks(ArrayList<Task> tasks, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "   _____________________________________________________________\n";
        String filePath = "./data/duke.txt"; // Default path

        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(horizontalLine + "    Hello! I'm EchoBot\n" + logo);
        System.out.println("    What can I do for you?\n" + horizontalLine);

        try {
            tasks = loadTasks(filePath);
        } catch (Exception e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }

        while(true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine + "    Here are the tasks in your list:");

                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println("    " + (i + 1) + "." + task.toString());
                }

                System.out.println(horizontalLine);
            } else if (userInput.startsWith("todo")) {
                try {
                    String taskDescription = extractTaskDesc(userInput, "todo");
                    if (taskDescription.isEmpty()) {
                        throw new DukeException(horizontalLine + "    ☹ OOPS!!! The description of a todo cannot be empty."
                                + "\n"+ horizontalLine);
                    }

                    Task newTodo = new Todo(taskDescription);
                    tasks.add(newTodo);

                    System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newTodo);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                    saveTasks(tasks, filePath); // Save after adding
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline")) {
                String taskDescription = extractTaskDesc(userInput, "deadline");
                int indexOfBy = taskDescription.indexOf("/by");
                String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
                String by = taskDescription.substring(indexOfBy + 3).trim();
                Task newDeadline = new Deadline(deadlineDescription, parseDate(by));
                tasks.add(newDeadline);

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newDeadline);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                saveTasks(tasks, filePath); // Save after adding
            } else if (userInput.startsWith("event")) {
                String taskDescription = extractTaskDesc(userInput, "event");
                int indexOfFrom = taskDescription.indexOf("/from");
                int indexOfTo = taskDescription.indexOf("/to");
                String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
                String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = taskDescription.substring(indexOfTo + 3).trim();
                Task newEvent = new Event(eventDescription, parseDateTime(from), parseDateTime(to));
                tasks.add(newEvent);

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newEvent);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                saveTasks(tasks, filePath); // Save after adding
            } else if (userInput.startsWith("mark")) {
                int taskNum = extractTaskNum(userInput, "mark");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task task = tasks.get(taskNum - 1);
                    task.mark();

                    System.out.println(horizontalLine + "    Nice! I've marked this task as done:");
                    System.out.print("      [" + task.getStatusIcon() + "] " + task.getDescription());

                    // Additional information
                    if (task instanceof Event) {
                        System.out.print(" (from: " + ((Event) task).from + " to: " + ((Event) task).to + ")");
                    } else if (task instanceof Deadline) {
                        System.out.print(" (by: " + ((Deadline) task).by + ")");
                    }

                    System.out.println("\n" + horizontalLine);
                }
                saveTasks(tasks, filePath); // Save after marking
            } else if (userInput.startsWith("unmark")) {
                int taskNum = extractTaskNum(userInput, "unmark");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task task = tasks.get(taskNum - 1);
                    task.unmark();

                    System.out.println(horizontalLine + "    OK, I've marked this task as not done yet:");
                    System.out.print("      [" + task.getStatusIcon() + "] " + task.getDescription());

                    // Additional information
                    if (task instanceof Event) {
                        System.out.print(" (from: " + ((Event) task).from + " to: " + ((Event) task).to + ")");
                    } else if (task instanceof Deadline) {
                        System.out.print(" (by: " + ((Deadline) task).by + ")");
                    }

                    System.out.println("\n" + horizontalLine);
                }
                saveTasks(tasks, filePath); // Save after unmarking
            } else if (userInput.startsWith("delete")) {
                int taskNum = extractTaskNum(userInput, "delete");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task deletedTask = tasks.remove(taskNum - 1);

                    System.out.println(horizontalLine + "    Noted. I've removed this task:\n" + "     " + deletedTask.toString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                }
                saveTasks(tasks, filePath); // Save after deleting
            } else {
                System.out.println(horizontalLine + "    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(horizontalLine);
            }
        }
    }
}