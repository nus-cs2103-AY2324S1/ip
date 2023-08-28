import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public enum Operation {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | | / _ \\\n"
                + "| |_| | |_| |  |_   __/\n"
                + "|____/ \\__,_|___|\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke!\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void delete(String userInput, TaskList taskList) throws TaskException, NotIntegerException {
        String[] parts = userInput.split(" ", 2);
        if (!isInteger(parts[1])) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(parts[1]);
        // error of out of bounds handled in TaskList itself
        // since the size is better/ easier to get in TaskList class
        taskList.deleteTask(taskIndex);
    }

    public static void mark(String userInput, TaskList taskList) throws EmptyDescriptionException,
            NotIntegerException, TaskException {
        String details = extractNoKeywordsDetails(userInput);
        if (!isInteger(details)) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(details);
        taskList.mark(taskIndex);
    }

    public static void unmark(String userInput, TaskList taskList) throws EmptyDescriptionException,
            TaskException, NotIntegerException {
        String details = extractNoKeywordsDetails(userInput);
        if (!isInteger(details)) {
            throw new NotIntegerException();
        }
        int taskIndex = Integer.parseInt(details);
        taskList.unMark(taskIndex);
    }

    public static void todo(String userInput, TaskList taskList) throws EmptyDescriptionException {
        String details = extractNoKeywordsDetails(userInput);
        Task todoTask = new Todo(details);
        taskList.addTask(todoTask);
    }

    public static void deadline(String userInput, TaskList taskList) throws EmptyDescriptionException, MissingKeywordException {
        String details = extractTaskDetails(userInput, "deadline", "/by");
        String date = extractAfterKeyword(userInput, "/by");
        Task deadlineTask = new Deadline(details, date);
        taskList.addTask(deadlineTask);
    }

    public static void event(String userInput, TaskList taskList) throws EmptyDescriptionException, MissingKeywordException {
        String details = extractTaskDetails(userInput, "event", "/from");
        String from = extractAfterKeyword(userInput, "/from", "/to");
        String to = extractAfterKeyword(userInput, "/to");
        Task eventTask = new Event(details, from, to);
        taskList.addTask(eventTask);
    }

    /*  helper functions */
    private static String extractNoKeywordsDetails(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.toLowerCase().split(" ", 2);
        if (parts.length == 1 || parts[1].isBlank()) {
            throw new EmptyDescriptionException("Description cannot be empty");
        }
        return parts[1];
    }

    /**
     * Extracts the task details in between `command` and `keyword`.
     * E.g. deadline read the book /by tuesday
     *      -> extracts `read the book`
     *
     * @param userInput string input by user.
     * @param commandAndKeyword todo/deadline/event
     * @return string in between command and keyword
     */
    private static String extractTaskDetails(String userInput, String... commandAndKeyword)
            throws EmptyDescriptionException, MissingKeywordException {
        String[] tokens = userInput.toLowerCase().split(commandAndKeyword[0], 2);
        if (tokens.length == 0 || tokens[1].isBlank()) {
            throw new EmptyDescriptionException("Task description cannot be empty");
        }
        String detailsWithKeyword = tokens[1];
        int endIndex = detailsWithKeyword.indexOf(commandAndKeyword[1]);
        if (endIndex == -1) {
            throw new MissingKeywordException("Missing keyword: " + commandAndKeyword[1]);
        }
        String toReturn = detailsWithKeyword.substring(0, endIndex).trim();
        if (toReturn.isEmpty()) {
            throw new EmptyDescriptionException("Task description cannot be empty");
        }
        return toReturn;
    }


    /**
     * Extracts the details after the keyword.
     * E.g. event go to school /from mon 2pm /to tues 3pm
     *      -> extracts `mon 2pm`
     *
     * @param userInput string input by user.
     * @param keywords the keywords in use
     * @return string after keyword/ between 2 keywords.
     */
    private static String extractAfterKeyword(String userInput, String... keywords) throws EmptyDescriptionException {
        String[] tokens = userInput.toLowerCase().split(keywords[0]);
        if (tokens.length == 1 || tokens[1].isBlank()) {
            throw new EmptyDescriptionException("Details after " + keywords[0] + " cannot be empty");
        }
        if (keywords.length == 1) {
            return tokens[1];
        }
        // last case if there are 2 keywords, such as /from and /to
        // -> return the in between of these 2 keywords
        String[] tokensAfterSecondKeyword = tokens[1].split(keywords[1]);
        if (tokensAfterSecondKeyword[0].isBlank()) {
            throw new EmptyDescriptionException("Details after " + keywords[0] + " cannot be empty");
        }
        return tokensAfterSecondKeyword[0];
    }

    /**
     * the method isInteger to judge whether input is integer
     * @param input
     * @return boolean
     */
    private static boolean isInteger(String input) {
        if (input == null) {
            return false;
        } else {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }
    }

    private static void saveTasksToFile(TaskList taskList) {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                if (dataDir.mkdirs()) {
                    System.out.println("Data directory created.");
                } else {
                    System.out.println("Data directory creation failed.");
                }
            }
            File file = new File("./data/duke.txt");
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Data file created.");
                } else {
                    System.out.println("Data file creation failed.");
                }
            }
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.returnTaskList()) {
                writer.write(task.changeFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        TaskList taskList = new TaskList();
        boolean ongoing = true;
        while(ongoing) {
            Operation operation;
            try {
//            System.out.print("> ");
                userInput = scanner.nextLine();
                try {
                    operation = Operation.valueOf(userInput.toUpperCase().split(" ")[0]);
                } catch (Exception e) {
                    throw new InvalidCommandException();
                }
                switch (operation) {
                case BYE:
                    ongoing = false;
                    exit();
                    break;
                case LIST:
                    taskList.listAllTasks();
                    break;
                case DELETE:
                    delete(userInput, taskList);
                    break;
                case MARK:
                    mark(userInput, taskList);
                    break;
                case UNMARK:
                    unmark(userInput, taskList);
                    break;
                case TODO:
                    todo(userInput, taskList);
                    break;
                case DEADLINE:
                    deadline(userInput, taskList);
                    break;
                case EVENT:
                    event(userInput, taskList);
                    break;
                default:
                    throw new InvalidCommandException();
                }
                saveTasksToFile(taskList);
            } catch (TaskException | InvalidCommandException | EmptyDescriptionException
                     | NotIntegerException | MissingKeywordException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
