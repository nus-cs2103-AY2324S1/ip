import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Chatbot implementation
 */
public class Duke {
    // Chatbot's name
    static final String NAME = "Atlas";
    // Task list save directory
    static final String LIST_DIRECTORY = "./data/";
    // Task list file name
    static final String LIST_FILENAME = "duke.txt";
    // Task list
    static List<Task> tasks = new ArrayList<>();
    // Supported task types
    protected enum TaskTypes {
        ToDo,
        Deadline,
        Event
    }

    /**
     * Exception class for unsupported task types
     */
    static class UnsupportedTaskType extends RuntimeException {
        final protected String taskType;

        /**
         * Constructs an UnsupportedTaskType exception
         * @param taskType Name of illegal task type
         */
        public UnsupportedTaskType(String taskType) {
            this.taskType = taskType;
        }

        /**
         * Constructs an UnsupportedTaskType exception
         * @param taskType Type of illegal task type
         */
        public UnsupportedTaskType(TaskTypes taskType) {
            this.taskType = taskType.toString();
        }

        public String getTaskType() {
            return taskType;
        }
    }

    /**
     * Main function
     * @param args Arguments to initialise chatbot (none so far)
     */
    public static void main(String[] args) {
        loadList();
        greet();
        listen();
    }

    /**
     * Prints greeting to console.
     */
    protected static void greet() {
        String logo =
                "        _______ _                _____ \n" +
                        "     /\\|__   __| |        /\\    / ____|\n" +
                        "    /  \\  | |  | |       /  \\  | (___  \n" +
                        "   / /\\ \\ | |  | |      / /\\ \\  \\___ \\ \n" +
                        "  / ____ \\| |  | |____ / ____ \\ ____) |\n" +
                        " /_/    \\_\\_|  |______/_/    \\_\\_____/ \n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.printf("Hello, I'm %s!\n", Duke.NAME);
        System.out.println("What would you like me to do today?");
        printHorizontalLine();
    }

    /**
     * Listens and executes commands
     */
    protected static void listen() {
        class UnknownCommandException extends RuntimeException {
            public UnknownCommandException(String command) {
                super("I don't know what this means :( You requested: " + command);
            }
        }
        String input, command, args;
        boolean exitChatbot = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!exitChatbot) {
            try {
                input = reader.readLine();
                printHorizontalLine();
                String[] splitInput = input.split(" ", 2);
                command = splitInput[0];
                args = splitInput.length > 1 ? splitInput[1] : "";
                switch (command) {
                    case "todo":
                        addTask(args, TaskTypes.ToDo);
                        break;
                    case "deadline":
                        addTask(args, TaskTypes.Deadline);
                        break;
                    case "event":
                        addTask(args, TaskTypes.Event);
                        break;
                    case "list":
                        printList();
                        break;
                    case "bye":
                        exitChatbot = true;
                        break;
                    case "mark":
                        toggleTaskStatus(args, true);
                        break;
                    case "unmark":
                        toggleTaskStatus(args, false);
                        break;
                    case "delete":
                        deleteTask(args);
                        break;
                    case "date":
                        printTaskOnDate(args);
                        break;
                    default:
                        throw new UnknownCommandException(command);
                }
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
                System.out.println("Here's what I can do though: I can create ToDos (todo),\n"
                        + "Deadlines (deadline), Events (event), print them out (list),\n"
                        + "print tasks occurring on a specific date (date) as well as \n"
                        + "check (mark) and uncheck (unmark) them!");
            } catch (IOException e) {
                System.out.println("Unable to read command, exiting");
                printHorizontalLine();
                exitChatbot = true;
            } finally {
                if (!exitChatbot) {
                    printHorizontalLine();
                }
            }
        }
        exit();
        printHorizontalLine();
    }

    /**
     * Adds task to list. If the list is already full, an error message is
     * instead printed and returned. If not, the item is added to the list.
     * @param args Item to add to list
     * @param taskType One of "todo", "deadline", or "event"
     */
    protected static void addTask(String args, TaskTypes taskType) {
        Task newTask;
        try {
            switch (taskType) {
                case ToDo:
                    newTask = createTodo(args);
                    break;
                case Deadline:
                    newTask = createDeadline(args);
                    break;
                case Event:
                    newTask = createEvent(args);
                    break;
                default:
                    throw new UnsupportedTaskType(taskType);
            }
            tasks.add(newTask);
            System.out.printf("Got it. I've added this task:\n"
                    + "\t%s\n", newTask);
            printTaskCount();
            saveList();
        } catch (IllegalArgumentException e) {
            System.out.printf("I think you missed something! %s\n", e.getMessage());
        } catch (UnsupportedTaskType e) {
            System.out.printf("I can't handle this task type: %s\n", e.getTaskType());
        } catch (DateTimeParseException e) {
            System.out.println("All date times must be of format dd-MM-yyyy HHmm");
        }
    }

    /**
     * Creates new Todo
     * @param name Name of Todo
     * @return new Todo with given name
     */
    protected static Todo createTodo(String name) {
        return new Todo(name);
    }

    /**
     * Create new Deadline
     * @param argString String containing name and date in the format "[name] \by [date]"
     * @return New Deadline with given name and date
     * @throws IllegalArgumentException argString improperly formatted: [name] \by [date]
     */
    protected static Deadline createDeadline(String argString) throws IllegalArgumentException {
        String[] args = argString.split(" /by ");
        if (args.length != 2) {
            throw new IllegalArgumentException("Deadlines should be created with the following format:\n"
                    + "deadline [name] /by [date]");
        }
        String name = args[0];
        String date = args[1];
        return new Deadline(name, date);
    }

    /**
     * Create new Event
     * @param argString String containing name, start time, and end time in the format
     *                  "[name] \from [start time] \to [end time]
     * @return New Event with given name, start time, and end time
     * @throws IllegalArgumentException argString improperly formatted: [name] \from [start time] \to [end time]
     */
    protected static Event createEvent(String argString) throws IllegalArgumentException {
        IllegalArgumentException badFormat = new IllegalArgumentException("Events should be created "
                + "with the following format:\n event [name] /from [start time] /to [end time]");
        String[] splitNameDates = argString.split(" /from ");
        if (splitNameDates.length != 2) {
            throw badFormat;
        }
        String name = splitNameDates[0];
        String[] splitTime = splitNameDates[1].split(" /to ");
        if (splitTime.length != 2) {
            throw badFormat;
        }
        String startTime = splitTime[0];
        String endTime = splitTime[1];
        return new Event(name, startTime, endTime);
    }

    /**
     * Writes task list to file
     */
    protected static void saveList() {
        final String filePath = LIST_DIRECTORY + LIST_FILENAME;
        try {
            File saveDir = new File(LIST_DIRECTORY);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            File saveFile = new File(filePath);
            if (saveFile.createNewFile()) {
                System.out.println("Save file created at: " + filePath);
            }
            try (FileWriter taskListWriter = new FileWriter(filePath)){
                for (Task t : tasks) {
                    taskListWriter.write(t.generateSaveString() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Unable to save task list to destination: " + filePath);
                System.out.println("Reason: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Unable to create file: " + filePath);
            System.out.println("Reason: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("Permission not granted to create file!");
        }
    }

    /**
     * Loads task list from file
     */
    protected static void loadList() {
        final String filePath = LIST_DIRECTORY + LIST_FILENAME;
        try {
            File fileToLoad = new File(filePath);
            Scanner listReader = new Scanner(fileToLoad);
            while (listReader.hasNextLine()) {
                String[] taskArgs = listReader.nextLine().split(" \\| ");
                if (taskArgs.length != 3) {
                    throw new IllegalArgumentException();
                }
                Task loadedTask;
                switch (taskArgs[0]) {
                    case "T":
                        loadedTask = createTodo(taskArgs[2]);
                        break;
                    case "D":
                        loadedTask = createDeadline(taskArgs[2]);
                        break;
                    case "E":
                        loadedTask = createEvent(taskArgs[2]);
                        break;
                    default:
                        throw new UnsupportedTaskType(taskArgs[0]);
                }
                if (taskArgs[1].equals("true")) {
                    loadedTask.markAsDone();
                }
                tasks.add(loadedTask);
            }
            listReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not load list from: " + filePath);
        } catch (IllegalArgumentException e) {
            System.out.println("Load file is corrupted, abandoning file read");
            tasks.clear();
        } catch (UnsupportedTaskType e) {
            System.out.printf("Unsupported file type %s, abandoning file read\n", e.getTaskType());
            tasks.clear();
        }
    }

    /**
     * Prints list in the order in which items are added.
     */
    protected static void printList() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf("%d.%s\n", i + 1, getTaskByOneIndex(i + 1));
        }
    }

    /**
     * Marks task as done
     * @param idx 1-based task index
     * @param checkTask Mark the task as done
     */
    protected static void toggleTaskStatus(String idx, boolean checkTask) {
        try {
            Task selectedTask = getTaskByOneIndex(idx);
            if (checkTask) {
                selectedTask.markAsDone();
                System.out.printf("Nice! I've marked this task as done:\n" +
                        "%s\n", selectedTask);
            } else {
                selectedTask.markAsNotDone();
                System.out.printf("OK, I've marked this task as not done yet:\n" +
                        "%s\n", selectedTask);
            }
            saveList();
        } catch (NumberFormatException e) {
            System.out.println("I need a positive integer to know which task you're referring to!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You're not referring to a valid task!");
        }
    }

    /**
     * Deletes task from task list
     * @param args String containing 1-based index of task to be deleted
     */
    protected static void deleteTask(String args) {
        try {
            int oneBasedIdx = parseTaskIndex(args);
            Task taskToDelete = getTaskByOneIndex(oneBasedIdx);
            tasks.remove(oneBasedIdx - 1);
            System.out.printf("Noted. I've removed this task:\n" +
                    "\t%s\n", taskToDelete);
            printTaskCount();
            saveList();
        } catch (NumberFormatException e) {
            System.out.println("I need a positive integer to know which task you are deleting!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You're not referring to a valid task!");
        }
    }

    /**
     * Converts index in string to an unsigned integer
     * @param args String containing 1-based index
     * @return Index converted to integer form
     */
    protected static int parseTaskIndex(String args) {
        return Integer.parseUnsignedInt(args, 10);
    }
    /**
     * Returns task from task list using 1-based index
     * @param args String containing task index (1-based index)
     * @return Task at selected index (e.g. if args is "2", returns 2nd task in list)
     */
    protected static Task getTaskByOneIndex(String args) {
        int idx = parseTaskIndex(args);
        return getTaskByOneIndex(idx);
    }

    /**
     * Returns task from task list using 1-based index
     * @param idx One-based index
     * @return Task at corresponding zero-based index in task list
     */
    protected static Task getTaskByOneIndex(int idx) {
        return tasks.get(idx - 1);
    }

    /**
     * Prints goodbye to console.
     */
    protected static void exit() {
        saveList();
        System.out.println("Goodbye!");
    }

    /**
     * Prints a horizontal line containing the character '-' of width 80.
     */
    protected static void printHorizontalLine() {
        final int consoleWidth = 80;
        final String line = "_".repeat(consoleWidth);
        System.out.println(line);
    }

    /**
     * Prints current number of tasks in task list
     */
    protected static void printTaskCount() {
        System.out.printf("Now you have %d %s in the list.\n", tasks.size(),
                tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Prints all tasks occurring on a particular date
     * @param dateInput String containing date with format dd-MM-yyyy
     */
    protected static void printTaskOnDate(String dateInput) {
        final String DATE_FORMAT = "dd-MM-yyyy";
        final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            LocalDate date = LocalDate.parse(dateInput, DATE_FORMATTER);
            System.out.printf("These tasks in your list occur on %s:\n", date);
            for (Task t : tasks) {
                if (t.isOccurringOnDate(date)) {
                    System.out.println(t);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date input, dates must be of format " + DATE_FORMAT);
        }

    }
}
