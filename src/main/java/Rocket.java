import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;


public class Rocket {
    private static final String LINE = "    ____________________________________________________________";
    private static final DateTimeFormatter formatUglyDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter formatPrettyDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public static void main(String[] args) {

        // Create data file and directory if they don't exist
        String directoryPath = "./data/";
        String filePath = directoryPath + "rocket.txt";

        // Make new folder if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Make new file if it doesn't exist
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                printError(e.getMessage());
            }
        }

        // Create list
        List<Task> taskList = new ArrayList<>();

        // Load Saved tasks
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskString = fileScanner.nextLine();
                try {
                    taskList.add(stringToTask(taskString));
                } catch (RocketIllegalArgumentException e) {
                    printError(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Read user input
        System.out.println(LINE + "\n    Hello! I'm Rocket\n" +
                "    What can I do for you?\n" + LINE);
        String input = scanner.nextLine();

        while (true) {
            // Split string to get command and arguments
            String[] CommandAndArguments = processInput(input);
            String command = CommandAndArguments[0];
            String arguments = CommandAndArguments[1];
            try {
                switch (command) {
                case "bye": {
                    try {
                        saveTaskList(taskList, filePath);
                    } catch (IOException e) {
                        printError(e.getMessage());
                    }
                    handleBye();
                    return;
                }
                case "list": {
                    handleList(taskList);
                    break;
                }
                case "mark": {
                    handleMark(taskList, arguments);
                    break;
                }
                case "unmark": {
                    handleUnmark(taskList, arguments);
                    break;
                }
                case "delete": {
                    handleDelete(taskList, arguments);
                    break;
                }
                case "todo": {
                    handleTodo(taskList, arguments);
                    break;
                }
                case "deadline": {
                    handleDeadline(taskList, arguments);
                    break;
                }
                case "event": {
                    handleEvent(taskList, arguments);
                    break;
                }
                default: {
                    throw new RocketInvalidCommandException();
                }
                }
            } catch (RocketException e) {
                printError(e.getMessage());
            } finally {
                if (!input.equals("bye")) {
                    input = scanner.nextLine();
                }
            }
        }
    }

    /**
     * Processes input into command and arguments.
     * @param input the user input.
     * @return an array where the first element is the command and the second argument is the arguments
     */
    private static String[] processInput(String input) {
        int firstWordIndex = input.indexOf(' ');
        if (firstWordIndex == -1) {
            return new String[]{input, ""};
        } else {
            return new String[]{input.substring(0, firstWordIndex),
                    input.substring(firstWordIndex + 1)};
        }
    }

    /**
     * Prints out a nicely-formatted error message
     * @param message the error message.
     */
    private static void printError(String message) {
        System.out.println(LINE);
        System.out.println("     ☹ OOPS!!! " + message);
        System.out.println(LINE);
    }

    private static Task stringToTask(String taskString) throws RocketIllegalArgumentException {
        char taskType = taskString.charAt(1);
        boolean isDone = taskString.charAt(4) == 'X';
        if (taskType == 'T') {
            return new Todo(taskString.substring(7), isDone);
        } else if (taskType == 'D') {
            return makeDeadline(taskString.substring(7), isDone, formatPrettyDateTime);
        } else {
            return makeEvent(taskString.substring(7), isDone, formatPrettyDateTime);
        }
    }


    /**
     * Saves tasklist in to a file
     * @param taskList the tasklist.
     * @param filePath the filePath that indicates where the file is located at.
     * @throws IOException occurs when there are problems writing to the file
     */
    private static void saveTaskList(List<Task> taskList, String filePath) throws IOException{
        writeToFile(filePath, "");
        for (Task task: taskList) {
            appendToFile(filePath, String.valueOf(task));
            appendToFile(filePath, System.lineSeparator());
        }
    }

    /**
     * Writes a string to a file
     * @param filePath the file path that indicates where the file is located at.
     * @param textToAdd the text to be added to the file
     * @throws IOException occurs when there are problems writing to the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends a string to a file
     * @param filePath the file path that indicates where the file is located at
     * @param textToAppend the text to be appended to the file
     * @throws IOException occurs when there are problems writing to the file
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * handles bye command
     */
    private static void handleBye() {
        System.out.println(LINE);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * handles List command
     * @param taskList the ArrayList that represents the tasklist
     */
    private static void handleList(List<Task> taskList) {
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("    " + (i + 1) + "." + task);
        }
        System.out.println(LINE);
    }

    /**
     * handle Mark command
     * @param taskList the ArrayList that represents the tasklist
     * @param arguments the command line arguments
     */
    private static void handleMark(List<Task> taskList, String arguments) {
        int taskNumber = Integer.parseInt(arguments) - 1;
        Task task = taskList.get(taskNumber);
        task.markAsDone();
        System.out.println(LINE);
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
        System.out.println(LINE);
    }

    /**
     * handle Unmark command
     * @param taskList the ArrayList that represents the tasklist
     * @param arguments the command line arguments
     */
    private static void handleUnmark(List<Task> taskList, String arguments) {
        int taskNumber = Integer.parseInt(arguments) - 1;
        Task task = taskList.get(taskNumber);
        task.markAsUndone();
        System.out.println(LINE);
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
        System.out.println(LINE);
    }

    /**
     * handle Delete command
     * @param taskList the ArrayList that represents the tasklist
     * @param arguments the command line arguments
     */
    private static void handleDelete(List<Task> taskList, String arguments) {
        int taskNumber = Integer.parseInt(arguments) - 1;
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        System.out.println(LINE);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * handle Todo command
     * @param taskList the ArrayList that represents the list of tasks
     * @param arguments the command line arguments
     * @throws RocketIllegalArgumentException throws illegal argument exception if there is no description provided
     */
    private static void handleTodo(List<Task> taskList, String arguments) throws RocketIllegalArgumentException {
        if (arguments.isBlank()) {
            throw new RocketIllegalArgumentException("The description of a Todo");
        }
        Todo todo = new Todo(arguments, false);
        taskList.add(todo);
        System.out.println(LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + todo);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * handle Deadline command
     * @param taskList the ArrayList that represents the list of tasks
     * @param arguments the command line arguments
     * @throws RocketIllegalArgumentException throws illegal argument exception if there is no
     *         description or deadline provided
     */
    private static void handleDeadline(List<Task> taskList, String arguments) throws RocketIllegalArgumentException {
        if (arguments.isBlank()) {
            throw new RocketIllegalArgumentException("The description of a Deadline");
        }
        Deadline deadline = makeDeadline(arguments, false, formatUglyDateTime);
        taskList.add(deadline);
        System.out.println(LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + deadline);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * handle Event command
     * @param taskList the ArrayList that represents the list of tasks
     * @param arguments the command line arguments
     * @throws RocketIllegalArgumentException throws illegal argument exception if there is no
     *         description or duration provided
     */
    private static void handleEvent(List<Task> taskList, String arguments) throws RocketIllegalArgumentException {
        if (arguments.isBlank()) {
            throw new RocketIllegalArgumentException("The description of an Event");
        }
        Event event = makeEvent(arguments, false, formatUglyDateTime);
        taskList.add(event);
        System.out.println(LINE);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + event);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
        System.out.println(LINE);
    }

    /**
     * Extracts deadline from input.
     * @param arguments information about task.
     * @return a deadline task.
     * @throws RocketIllegalArgumentException because of illegal argument.
     */
    private static Deadline makeDeadline(String arguments, boolean isDone, DateTimeFormatter formatter) throws RocketIllegalArgumentException {
        int descriptionIndex = arguments.indexOf("by") - 2;
        String description = arguments.substring(0, descriptionIndex);
        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of a deadline");
        }
        String deadline = arguments.substring(descriptionIndex + 5)
                .replace(')', ' ')
                .trim();
        LocalDateTime by = stringToDateTime(deadline, formatter);
        if (deadline.isEmpty()) {
            throw new RocketIllegalArgumentException("The deadline of a deadline");
        }
        return new Deadline(description, isDone, by);
    }

    /**
     * Extracts event from input.
     * @param arguments information about event.
     * @return an event.
     * @throws RocketIllegalArgumentException because of Illegal Argument.
     */
    private static Event makeEvent(String arguments, boolean isDone, DateTimeFormatter formatter) throws RocketIllegalArgumentException{
        int descriptionIndex = arguments.indexOf("from") - 2;
        String description = arguments.substring(0, descriptionIndex);
        if (description.isEmpty()) {
            throw new RocketIllegalArgumentException("The description of an event.");
        }
        String duration = arguments.substring(descriptionIndex + 7)
                .trim();
        if (duration.isBlank()) {
            throw new RocketIllegalArgumentException("The duration of an event");
        }
        int fromIndex = duration.indexOf("to") - 1;
        LocalDateTime from = stringToDateTime(
                duration.substring(0, fromIndex).trim(),
                formatter
        );
        LocalDateTime to = stringToDateTime(
                duration.substring(fromIndex + 4)
                        .replace(')', ' ')
                        .trim(),
                formatter
        );
        return new Event(description, isDone, from, to);
    }

    private static LocalDateTime stringToDateTime(String s, DateTimeFormatter formatter) {
        return LocalDateTime.parse(s, formatter);
    }

}
