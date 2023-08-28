import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = "I'm Chewy,\n" +
                "What can I do for you?\n";
        System.out.println("Hello!\n" + logo);

        // Create a scanner object to read commands entered by the user
        Scanner scanner = new Scanner(System.in);

        // Store tasks entered by the user in tasks
        List<Task> tasks = new ArrayList<>();

        // Load tasks
        loadTasksFromFile(tasks);

        // Start command loop
        while (true) {
            // Read the next line of input
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("help")) {
                    HelpMessage.displayHelpMessage();
                } else if (userInput.equals("list")) {
                    listTasks(tasks);
                } else if (userInput.startsWith("mark")) {
                    markTaskAsDone(userInput, tasks);
                    saveTask(tasks);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTaskAsDone(userInput, tasks);
                    saveTask(tasks);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput, tasks);
                    saveTask(tasks);
                } else {
                    addTask(userInput, tasks);
                    saveTask(tasks);
                }
            } catch (DukeException e) {
                System.out.println(":( Chewy can't understand! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something unexpected happened. try 'help' to see a list of commands");
            }
        }
    }

    private static void saveTask(List<Task> tasks) {
        System.out.println("Saving tasks...");
        try (PrintWriter writer = new PrintWriter("./data/duke.txt")) {
            for (Task task: tasks) {
                writer.println(task.toFileString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file" + e.getMessage());
        }
        System.out.println("Task saved successfully");
    }

    private static void loadTasksFromFile(List<Task> tasks) {
        System.out.println("Loading tasks...");
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/duke.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFileString(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Cant load file!");
        }
        System.out.println("Tasks loaded successfully, see 'list' to view all tasks");
    }

    private static Task parseTaskFromFileString(String lineFromFile) {
        Task task;
        String[] lineParts = lineFromFile.split(" \\| ");
        String taskType = lineParts[0];
        boolean isDone = lineParts[1].equals("X");
        String description = lineParts[2];
        switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by;
                if (lineParts.length == 4) {
                    by = lineParts[3];
                    task = new Deadline(description, by); // add by
                } else {
                    task = new Deadline(description);
                }
                break;
            case "E":
                String from;
                String to;
                if (lineParts.length == 5) {
                    from = lineParts[3];
                    to = lineParts[4];
                    task = new Event(description, from, to); // add from and to
                } else {
                    task = new Event(description);
                }
                break;
            default:
                // Line does not start with a task value (T,D,E), file corrupted, throw error
                throw new IllegalArgumentException("Invalid Task Type");
        }
        if (isDone) task.markAsDone();
        return task;
    }

    private static void addTask(String userInput, List<Task> tasks) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        // Check if a valid addTask command is entered
        String inputCommand = inputParts[0];
        if (!(inputCommand.equals("todo") || inputCommand.equals("deadline") || inputCommand.equals("event"))) {
            throw new InvalidCommandException();
        }

        // Throw InvalidCommandException if there is no description
        if (inputParts.length == 1) throw new NoDescriptionException();

        String taskDesc = inputParts[1];
        Task task;
        switch (inputCommand) {
            case "todo":
                task = new Todo(taskDesc);
                break;
            case "deadline":
                task = new Deadline(taskDesc);
                break;
            case "event":
                task = new Event(taskDesc);
                break;
            default:
                // Throw InvalidCommandException as invalid command was entered
                throw new DukeException("Error, see 'help' for a list of commands");
        }
        // Add task to task list
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n\t" + task);
        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
    }

    private static void deleteTask(String userInput, List<Task> tasks) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);
        if (inputParts.length != 2) throw new NoTaskFoundException();
        try {
            int taskId = Integer.parseInt(inputParts[1].trim());
            if (taskId < 1 || taskId > tasks.size()) throw new InvalidTaskException();
            Task deletedTask = tasks.remove(taskId - 1);
            System.out.println("Noted. I've removed this task:\n\t" + deletedTask);
            System.out.printf("Now you have %d tasks in the list%n", tasks.size());
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static void unmarkTaskAsDone(String userInput, List<Task> tasks) throws DukeException {
        try {
            String[] inputParts = userInput.split(" ", 2);
            // Throw NoTaskFoundException if there is nothing after 'unmark'
            if (inputParts.length != 2) throw new NoTaskFoundException();
            // Get the id of the task (zero-indexed) from the userInput
            int taskId = Integer.parseInt(inputParts[1]);
            // Throw InvalidTaskException if task id is out of bounds of task list or is invalid
            if (taskId <= 0 || taskId > tasks.size()) throw new InvalidTaskException();
            // Unmark the selected task as done
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + selectedTask);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static void markTaskAsDone(String userInput, List<Task> tasks) throws DukeException {
        try {
            String[] inputParts = userInput.split(" ", 2);
            // Throw NoTaskFoundException if there is nothing after 'mark'
            if (inputParts.length != 2) throw new NoTaskFoundException();
            // Get the id of the task (zero-indexed) from the userInput
            int taskId = Integer.parseInt(inputParts[1]);
            // Throw InvalidTaskException if task id is out of bounds of task list or is invalid
            if (taskId <= 0 || taskId > tasks.size()) throw new InvalidTaskException();
            // Mark the selected task as done
            Task selectedTask = tasks.get(taskId - 1);
            selectedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + selectedTask);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static void listTasks(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Chewy detected no task for you!");
        } else {
            System.out.println("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String taskLine = String.format("%d.%s",
                        i + 1,
                        task.toString());
                System.out.println(taskLine);
            }
        }
    }
}