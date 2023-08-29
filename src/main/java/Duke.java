import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    static String FILE_NAME = "./data/duke.txt";
    public enum CommandType {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        MARK,
        UNMARK,
        BYE,
        UNKNOWN
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }
    static String horizontal_line = "____________________________________________________________\n";

    /**
     * The main  method for the Duke application.
     * It initializes the task list and listens for user commands,
     * handling them appropriately based on their type.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        List<Task> tasks = loadData();
        handleStart();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String userCommand = scanner.nextLine();
            CommandType commandType = getCommandType(userCommand);
            switch(commandType) {
                case LIST:
                    handleList(tasks);
                    break;
                case BYE:
                    handleBye(scanner);
                    updateData(tasks);
                    isRunning = false;
                    break;
                case DELETE:
                    handleDelete(userCommand,tasks);
                    break;
                case MARK:
                    handleMark(userCommand, tasks);
                    break;
                case UNMARK:
                    handleUnmark(userCommand, tasks);
                    break;
                case EVENT:
                    handleEvent(userCommand, tasks);
                    break;
                case DEADLINE:
                    handleDeadline(userCommand, tasks);
                    break;
                case TODO:
                    handleTodo(userCommand, tasks);
                    break;
                case UNKNOWN:
                    handleUnknown();
                    break;
            }
        }
    }

    /**
     * Determines type of command entered by the user.
     *
     * @param userCommand The command string entered by the user.
     * @return The type of command.
     */
    public static CommandType getCommandType(String userCommand) {
        if ("bye".equals(userCommand)) {
            return CommandType.BYE;
        } else if ("list".equals(userCommand)) {
            return CommandType.LIST;
        } else if (userCommand.startsWith("delete ")) {
            return CommandType.DELETE;
        } else if (userCommand.startsWith("mark ")) {
           return CommandType.MARK;
        } else if (userCommand.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (userCommand.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (userCommand.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (userCommand.startsWith("event ")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    public static List<Task> loadData() {
        List<Task> tasks;
        try {
            tasks = loadFromFile(FILE_NAME);
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public static void updateData(List<Task> tasks) {
        try {
            saveToFile(tasks);
        } catch (IOException e) {
            System.out.println("An error occured while saving tasks.");
        }
    }

    public static void saveToFile(List<Task> tasks) throws IOException{
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(FILE_NAME)) {  // Using try-with-resources for automatic closure of writer
            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");  // Convert each task to its file string representation
            }
        }
    }

    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
    
        if (task instanceof ToDos) {
            sb.append("T | ");
        } else if (task instanceof Deadlines) {
            sb.append("D | ");
        } else if (task instanceof Events) {
            sb.append("E | ");
        }
    
        sb.append(task.isCompleted() ? "1" : "0").append(" | ");
        sb.append(task.getDescription());
    
        if (task instanceof Deadlines) {
            sb.append(" | ").append(((Deadlines) task).getDate());
        } else if (task instanceof Events) {
            sb.append(" | ").append(((Events) task).getStartDate()).append(" | ").append(((Events) task).getEndDate());
        }
    
        return sb.toString();
    }

    public static List<Task> loadFromFile(String filename) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
    
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                
                if (!isValidFormat(line)) {
                    throw new DukeException("Data file is corrupted. Line not in expected format: " + line);
                }

                Task task = parseTask(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with an empty task list.");
        }
        return tasks;

    }

    public static boolean isValidFormat(String line) {
        return line.matches("^[TDE] \\| [01] \\| .+");
    }

    public TaskType getTaskType(String str) {
        if (str.startsWith("T")) {
            return TaskType.TODO;
        } else if (str.startsWith("D")) {
            return TaskType.DEADLINE;
        } else if (str.startsWith("E")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }
    
    public static Task parseTask(String line) throws DukeException{
        String[] parts = line.split("\\|");
    
        // Trim spaces for each part
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        
        // Extract fields
        Task task = null;
        String taskType = parts[0];
        boolean completed = "1".equals(parts[1]);
        String description = parts[2];
        LocalDateTime date = (parts.length >= 4 && !parts[3].isEmpty()) ? LocalDateTime.parse(parts[3]) : null; 
        LocalDateTime startDate = (parts.length >= 5 && !parts[4].isEmpty()) ? LocalDateTime.parse(parts[4]) : null;
        LocalDateTime endDate = (parts.length == 6 && !parts[5].isEmpty()) ? LocalDateTime.parse(parts[5]) : null;
        
        try {
            switch(taskType) {
            case "T":
                task = new ToDos(description, completed);
                break;
        
            case "D":
                task = new Deadlines(description, date, completed);
                break;
        
            case "E":
                task = new Events(description, startDate, endDate, completed);
                break;
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        if (task == null) {
            throw new DukeException("Invalid task format.");
        }

        return task;
    }
    
    
    
    /**
     * Generates a message showing number of tasks in the list.
     *
     * @param numTasks The number of tasks currently in the list.
     * @return The formatted message.
     */
    public static String updateNumMessage(String numTasks) {
        return "Now you have " + numTasks + " task(s) in the list";
    }

    /**
     * Handles cases when the user provides an unrecognized command.
     * Outputs an error message to inform the user that the command is unknown.
     */
    public static void handleUnknown() {
        System.out.println("unknown command");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks A list of tasks to be displayed.
     * Each task is enumerated starting from 1 and printed using its string representation.
     */
    public static void handleList(List<Task> tasks) {
        System.out.println(horizontal_line);
        System.out.println("here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println(horizontal_line);
    }

    /**
     * Handles the deletion of a task from the list based on the user command.
     *
     * @param userCommand The command given by the user to delete a task.
     * @param tasks A list of tasks from which a task will be deleted.
     */
    public static void handleDelete(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ", 2);
        Task removedTask;
        try {
            if (parts.length != 2) {
                throw new DukeException("invalid delete command");
            }
            int num = Integer.parseInt(parts[1]);
            if (num > tasks.size()) {
                throw new DukeException("invalid delete command (this task number does not exist)");
            }
            removedTask = tasks.remove(num - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println(updateNumMessage(String.valueOf(tasks.size())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the marking of a task as completed based on the user command.
     *
     * @param userCommand The command given by the user to mark a task as completed.
     * @param tasks A list of tasks from which a specific task will be marked as completed.
     */
    public static void handleMark(String userCommand, List<Task> tasks) {
        String[] parts = userCommand.split(" ");
        try {
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else {
                throw new DukeException("invalid mark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the unmarking of a task, reverting its status to not completed based on the user command.
     *
     * @param userCommand The command given by the user to unmark a task.
     * @param tasks A list of tasks from which a specific task will be unmarked.
     */
    public static void handleUnmark(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ");
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.get(num - 1);
                task.toggleCompleted();
                System.out.println("Okay. I see you haven't done this task yet");
                System.out.println(task);
            } else {
                throw new DukeException("invalid unmark command");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialises and displays the welcome message for the Duke application.
     */
    public static void handleStart() {
        String welcomeMessage = horizontal_line +
                " Hello! I'm Blob\n" +
                " What can I do for you?\n" +
                horizontal_line;
        System.out.println(welcomeMessage);
    }

    /**
     * Adds a "todo" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleTodo(String userCommand, List<Task> tasks) {
        try {
            String description ="";
            String[] parts = userCommand.split(" ", 2);
            if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                description = parts[1];
            }
            Task currTask = new ToDos(description);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a "deadline" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleDeadline(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ", 2);
            String description = "";
            LocalDateTime date = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String secondPart = "";
            if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                secondPart = parts[1];
            }
            String[] finalParts = secondPart.split(" /by ", 2);
            if (finalParts.length >= 2) {
                description = finalParts[0];
                date = LocalDateTime.parse(finalParts[1], formatter);
            }
            Task currTask = new Deadlines(description, date);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (DateTimeParseException e) {
            System.out.println("The date format you provided is invalid. Please use the format /by yyyy-MM-dd HH:mm.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an "event" task to the task list and provides user feedback.
     *
     * @param userCommand The user input command string.
     * @param tasks The current list of tasks.
     */
    public static void handleEvent(String userCommand, List<Task> tasks) {
        try {
            String[] parts = userCommand.split(" ", 2);
            String description = "";
            LocalDateTime fromDate = null;
            LocalDateTime byDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (parts.length >= 2) {
                String[] secondPartSplits = parts[1].split(" /from ", 2);
                if (secondPartSplits.length >= 2) {
                    String[] dates = secondPartSplits[1].split(" /to ", 2);
                    if (dates.length >= 2) {
                        fromDate = LocalDateTime.parse(dates[0], formatter);
                        byDate = LocalDateTime.parse(dates[1], formatter);
                        description = secondPartSplits[0];
                    }
                }
            }
            Task currTask = new Events(description, fromDate, byDate);
            tasks.add(currTask);
            String numTasks = String.valueOf(tasks.size());
            System.out.println("Got it I have added this task:" + "\n" +  currTask);
            System.out.println(updateNumMessage(numTasks));
            System.out.println(horizontal_line);
        } catch (DateTimeParseException e) {
            System.out.println("The date format you provided is invalid. Please use the format yyyy-MM-dd HH:mm.");    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Closes the scanner and sends a bye message to the user.
     *
     * @param scanner The scanner used for user input.
     */
    public static void handleBye(Scanner scanner) {
        scanner.close();
        System.out.println(horizontal_line);
        System.out.println(" Bye. Come talk to Blob again soon!");
        System.out.println(horizontal_line);
    }
}