import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /** Divider constant */
    private static final String DIVIDER = "      ____________________________________________________________";

    /**
     * The file path for storing and retrieving tasks. */
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    /**
     * Method that marks a given task in a list
     * @param list List containing user inputs.
     * @param index Index of the given task.
     */
    public static void markAsDone(List<Task> list, int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            task.markAsDone();

            System.out.println(DIVIDER);
            System.out.println("        Nice! I've marked this task as done:");
            System.out.println("            " + task);
            System.out.println(DIVIDER);

        } else {
            System.out.println(DIVIDER);
            System.out.println("        Oops! Task not found!");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Method that unmark a given task in a list
     * @param list List containing user inputs.
     * @param index Index of the given task.
     */
    public static void markAsUndone(List<Task> list, int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            task.markAsUndone();

            System.out.println(DIVIDER);
            System.out.println("         OK! I've marked this task as not done yet:");
            System.out.println("            " + task);
            System.out.println(DIVIDER);

        } else {
            System.out.println(DIVIDER);
            System.out.println("        Oops! Task not found!");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Method that deletes a task from the list.
     * @param list List containing user inputs.
     * @param index Index of the task that wants to be deleted.
     */
    public static void deleteTask(List<Task> list, int index) {
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            list.remove(index);

            System.out.println(DIVIDER);
            System.out.println("        Noted. I've removed this task:");
            System.out.println("            " + task);
            System.out.println("        Now you have " + list.size() + " tasks in the list.");
            System.out.println(DIVIDER);

        } else {
            System.out.println(DIVIDER);
            System.out.println("        Oops! Task not found!");
            System.out.println(DIVIDER);
        }
    }

    /**
     * Loads tasks from a file and populates the provided list with the loaded tasks.
     * This method reads tasks from a file specified by the DATA_FILE_PATH and adds them to
     * the provided list of tasks. If the file does not exist, it will be created.
     *
     * @param list The list to which loaded tasks will be added.
     */
    private static void loadTasks(List<Task> list) {
        try {
            // Create a File object representing the data file
            File file = new File(DATA_FILE_PATH);

            // Create the file if it doesn't exist and print a message
            if (file.createNewFile()) {
                System.out.println("        New File Created");
                System.out.println(DIVIDER);
            }

            // Use a Scanner to read tasks from the file
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    // Read each line from the file
                    String line = sc.nextLine();
                    // Convert the line to a Task object using Task.fromString
                    Task task = Task.fromString(line);
                    // Add the task to the provided list if it's not null
                    if (task != null) {
                        list.add(task);
                    }
                }
            }

        } catch (IOException e) {
            // Handle IO exception by printing an error message
            System.out.println("        An error occurred while loading tasks");
        }
    }

    /**
     * Saves tasks from a list to a file.
     * This method writes tasks from the provided list to a file specified by the DATA_FILE_PATH.
     * The tasks are converted to their file representation using the toFileString method of
     * the Task class. If an error occurs during the file writing process, an error message
     * is printed.
     *
     * @param list The list of tasks to be saved to the file.
     */
    private static void saveTasks(List<Task> list) {
        try {
            // Create a File object representing the data file
            File file = new File(DATA_FILE_PATH);

            // Create a FileWriter to write tasks to the file
            FileWriter writer = new FileWriter(file);

            // Write each task's file representation to the file
            for (Task task : list) {
                writer.write(task.toFileString() + "\n");
            }

            // Close the FileWriter
            writer.close();

        } catch (IOException e) {
            // Handle IO exception by printing an error message
            System.out.println("        An error occurred while saving tasks");
        }
    }

    public static void main(String[] args) {
        // Opening lines
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);

        // Creates data folder if doesn't exist
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdir()) {
                System.out.println("        Data directory created successfully.");
            } else {
                System.out.println("        Failed to create data directory.");
            }
            System.out.println(DIVIDER);
        }

        // Check user input
        Scanner sc = new Scanner(System.in);
        List<Task> userList = new ArrayList<>();
        loadTasks(userList); // Load tasks to list

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // If user wants to quit
                saveTasks(userList); // Save changes to list
                System.out.println(DIVIDER);
                System.out.println("        Bye ~ Hope to see you again soon ~");
                System.out.println(DIVIDER);
                break;

            } else if (userInput.equals("list")) {
                // If user wants to check list
                System.out.println(DIVIDER);
                System.out.println("        Sure! Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    String str = String.format("            %d. %s", i + 1, userList.get(i));
                    System.out.println(str);
                }
                System.out.println(DIVIDER);

            } else if (userInput.startsWith("mark")) {
                // If user wants to mark something
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        markAsDone(userList, index);
                    } catch (NumberFormatException e) {
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid task number!");
                        System.out.println(DIVIDER);
                    }
                } else {
                    // Invalid command format
                    System.out.println(DIVIDER);
                    System.out.println("        Oops! Invalid command format!");
                    System.out.println(DIVIDER);
                }

            } else if (userInput.startsWith("unmark")) {
                // If user wants to unmark something
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        markAsUndone(userList, index);
                    } catch (NumberFormatException e) {
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid task number!");
                        System.out.println(DIVIDER);
                    }
                } else {
                    // Invalid command format
                    System.out.println(DIVIDER);
                    System.out.println("        Oops! Invalid command format!");
                    System.out.println(DIVIDER);
                }

            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                // User creates a task
                Task newTask = null;

                if (userInput.startsWith("todo")) {
                    // If user wants to create a ToDos task
                    if (userInput.length() < 5) {
                        // Incorrect input format for todos
                        System.out.println(DIVIDER);
                        System.out.println("        OOPS!! Incorrect input format for todo!!");
                        System.out.println(DIVIDER);
                    } else {
                        String description = userInput.substring(5).trim();
                        if (description.isBlank()) {
                            // If there's do description
                            System.out.println(DIVIDER);
                            System.out.println("        OOPS!! The description of a todo cannot be empty!!");
                            System.out.println(DIVIDER);
                        } else {
                            newTask = new ToDos(description);
                            userList.add(newTask);
                        }
                    }

                } else if (userInput.startsWith("deadline")) {
                    // If user wants to create a Deadlines task
                    String[] parts = userInput.split("/by");

                    if (parts.length == 2) {
                        // Makes sure deadline format is followed (e.g. there's /by)
                        String description = parts[0].substring(9).trim();
                        String by = parts[1].trim();
                        if (description.isBlank() || by.isBlank()) {
                            // If description or by is empty
                            System.out.println(DIVIDER);
                            System.out.println("        OOPS!! The description or by of a deadline or cannot be empty!!");
                            System.out.println(DIVIDER);
                        } else {
                            newTask = new Deadlines(description, by);
                            userList.add(newTask);
                        }
                    } else {
                        // Incorrect input format for deadline
                        System.out.println(DIVIDER);
                        System.out.println("        OOPS!! Incorrect input format for deadline!!");
                        System.out.println(DIVIDER);
                    }

                } else {
                    // If user wants to create an Events task
                    String[] parts = userInput.split("/from|/to");

                    if (parts.length == 3) {
                        // Makes sure deadline format is followed (e.g. there's /from and /to)
                        String description = parts[0].substring(6).trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();

                        if (description.isBlank() || from.isBlank() || to.isBlank()) {
                            // If description, from, or to is empty
                            System.out.println(DIVIDER);
                            System.out.println("        OOPS!! The description or from or to of an event or cannot be empty!!");
                            System.out.println(DIVIDER);
                        } else {
                            newTask = new Events(description, from, to);
                            userList.add(newTask);
                        }
                    } else {
                        // Incorrect input format for events
                        System.out.println(DIVIDER);
                        System.out.println("        OOPS!! Incorrect input format for event!!");
                        System.out.println(DIVIDER);
                    }
                }

                if (newTask != null) {
                    // If task is initialized
                    System.out.println(DIVIDER);
                    System.out.println("        Got it! I've added this task: ");
                    System.out.println("            " + newTask);
                    System.out.println("        Now you have " + userList.size() + " tasks in the list.");
                    System.out.println(DIVIDER);
                }

            } else if (userInput.startsWith("delete")) {
                // If user wants to delete a task
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        deleteTask(userList, index);
                    } catch (NumberFormatException e) {
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid task number!");
                        System.out.println(DIVIDER);
                    }
                } else {
                    // Invalid command format
                    System.out.println(DIVIDER);
                    System.out.println("        Oops! Invalid command format!");
                    System.out.println(DIVIDER);
                }

            } else {
                // Other inputs
                System.out.println(DIVIDER);
                System.out.println("        OOPS!! I'm sorry, but I don't know what that means...");
                System.out.println(DIVIDER);
            }
        }
    }
}