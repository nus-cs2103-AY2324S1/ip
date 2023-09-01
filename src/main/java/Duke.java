import Exceptions.DukeException;
import Storage.Storage;
import Tasks.*;
import UI.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Duke {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
//    private static Tasks.TaskList listContainer = new Tasks.TaskList();

    private static String NAME_EMPTY = "\uD83D\uDE21 Your item name cannot be empty!";
    private static String UNKNOWN_COMMAND = "\uD83D\uDE21 This command is not something I can handle!";
    private static String DEADLINE_EMPTY = "\uD83D\uDE21 Missing deadline!";

    private static String FROM_EMPTY = "\uD83D\uDE21 Missing from!";
    private static String TO_EMPTY = "\uD83D\uDE21 Missing to!";
    private static String TIME_FORMAT_ERROR = "\uD83D\uDE21 Time format invalid!";
    private static String FILE_PARSE_ERROR = "\uD83D\uDE21 Error parsing save file!";
    private static String INVALID_DATE_FORMAT = "\uD83D\uDE21 Invalid date format! Try using YYYY-MM-DD";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Path SAVE_FILE_LOCATION = Paths.get("src", "data", "duke.txt");
    private static Path SAVE_FILE_DIR = Paths.get("src", "data");


    private Storage storage;
    private Ui ui;
    private TaskList listContainer = new TaskList(new ArrayList<>());

    public Duke(String filePath) {

        this.storage = new Storage(filePath);

        try {
            this.listContainer = new TaskList(storage.load());
            ui = new Ui(this.listContainer, this.storage);
        } catch (DukeException e) {
            System.out.println(e.printError());
        }
    }

    private void run() {
        ui.beginLogging();

    }


    public static void main(String[] args) {
        new Duke(String.valueOf(SAVE_FILE_LOCATION)).run();


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println(SEPARATOR_LINE);
//        String entranceMsg = "Hello! I'm Elon Musk.\n" +
//                "What can I do for you?";
//        System.out.println(entranceMsg);
//        System.out.println(SEPARATOR_LINE);
//
//        // Load in existing file if it exists
//        try {
//
//
//            if (Files.notExists(SAVE_FILE_DIR)) {
//                // create the directory
//                System.out.println("⏳ Directory not present, creating...");
//                Files.createDirectory(SAVE_FILE_DIR);
//            }
//            if (Files.exists(SAVE_FILE_LOCATION)) {
//                System.out.println("⏳ Save file already exists, loading previous data");
//                // it exists, so let's read it
//
//                Scanner sc = new Scanner(SAVE_FILE_LOCATION);
//                while (sc.hasNextLine()) {
//                    String inputLine = sc.nextLine();
//                    if (inputLine.isEmpty()) {
//                        continue;
//
//                    }
//                    Task task = parseTask(inputLine);
//
//                    listContainer.addToList(task);
//
//                }
//                System.out.println("✅ Loaded " + listContainer.getSize() + " previous tasks.");
//
//            } else {
//
//                File saveFile = new File(String.valueOf(SAVE_FILE_LOCATION));
//
//                if (saveFile.createNewFile()) {
//                    System.out.println("✅ Created save file!");
//                } else {
//                    System.out.println("⚠\uFE0F Could not create save file!");
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(FILE_PARSE_ERROR);
//        }
//
//
//        String inputString = "";
//
//        Scanner keyboard = new Scanner(System.in);
//
//
//        loop:
//        while (true) {
//            try {
//                inputString = keyboard.nextLine();
//
//
//                String inputCommandString = (inputString.split(" ")[0].toUpperCase());
//
//                if (!Commands.contains(inputCommandString)) {
//                    throw new DukeException(UNKNOWN_COMMAND);
//                }
//
//                Commands inputCommand = Commands.valueOf(inputCommandString);
//
//                System.out.println(SEPARATOR_LINE);
//                switch (inputCommand) {
//                    case BYE: {
//                        printResult(inputCommand, null);
//                        break loop;
//                    }
//                    case LIST: {
//                        printResult(inputCommand, null);
//                        break;
//                    }
//                    case MARK: {
//                        // check if is number
//                        int index = Integer.parseInt(inputString.split(" ")[1]);
//                        Task markedTask = listContainer.markAsDone(index);
//
//                        printResult(inputCommand, markedTask);
//                        break;
//                    }
//                    case UNMARK: {
//                        int index = Integer.parseInt(inputString.split(" ")[1]);
//                        Task unmarkedTask = listContainer.markAsUnDone(index);
//
//                        printResult(inputCommand, unmarkedTask);
//                        break;
//                    }
//                    case DELETE: {
//                        int index = Integer.parseInt(inputString.split(" ")[1]);
//                        Task removedTask = listContainer.removeFromList(index);
//
//                        printResult(inputCommand, removedTask);
//                        break;
//                    }
//                    case TODO: {
//                        // add a todo
//                        String itemName = inputString.replace("todo ", "");
//
//                        if (itemName.isEmpty()) {
//                            // no item name
//                            throw new DukeException(NAME_EMPTY);
//                        }
//
//
//                        TodoTask todoTask = new TodoTask(itemName);
//
//                        listContainer.addToList(todoTask);
//
//                        printResult(inputCommand, todoTask);
//                        break;
//                    }
//                    case DEADLINE: {
//                        // format of entry: "deadline return book /by Sunday"
//                        String itemName = inputString.replace("deadline ", "").split(" /by ")[0];
//
//                        if (itemName.isEmpty()) {
//                            // no item name
//                            throw new DukeException(NAME_EMPTY);
//                        }
//
//                        String[] inputArgs = inputString.replace("deadline ", "").split(" /by ");
//                        if (inputArgs.length < 2) {
//                            // missing deadline
//                            throw new DukeException(DEADLINE_EMPTY);
//                        }
//                        String deadline = inputArgs[1];
//
//                        if (deadline.isEmpty()) {
//                            // no item name
//                            throw new DukeException(DEADLINE_EMPTY);
//                        }
//
//                        // parse the deadline - should be a LocalDate format
//
//                        try {
//                            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadline, formatter);
//
//                            DeadlineTask deadlineTask = new DeadlineTask(itemName, deadlineDateTime);
//
//                            listContainer.addToList(deadlineTask);
//
//                            printResult(inputCommand, deadlineTask);
//                        } catch (DateTimeParseException e) {
//                            throw new DukeException(INVALID_DATE_FORMAT);
//                        }
//
//
//                        break;
//                    }
//                    case EVENT: {
//                        String inputArgs = inputString.replace("event ", "");
//
//                        // sample format: event project meeting /from Mon 2pm /to 4pm
//                        // get the name
//                        String itemName = inputArgs.split(" /from ")[0];
//
//                        if (itemName.isEmpty()) {
//                            // no item name
//                            throw new DukeException(NAME_EMPTY);
//                        }
//
//                        // get the 'from...to'
//                        // @see https://stackoverflow.com/questions/4662215/how-to-extract-a-substring-using-regex
//                        Pattern patternFrom = Pattern.compile("(/from )(.*?)( /to)");
//                        Matcher matcherFrom = patternFrom.matcher(inputArgs);
//
//                        String from = "";
//                        if (matcherFrom.find()) {
//                            // yes, formatted correctly
//                            from = matcherFrom.group(2);
//                        } else {
////                        System.out.println("ERROR: no pattern found");
//                            throw new DukeException(TIME_FORMAT_ERROR);
//                        }
//
//                        // parse the 'from'
//                        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
//
//
//                        // get the to...
//                        String to = inputArgs.split("/to ")[1];
//
//                        if (to.isEmpty()) {
//                            throw new DukeException(TO_EMPTY);
//                        }
//                        // parse the 'to'
//                        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
//
//                        EventTask eventTask = new EventTask(itemName, dateTimeFrom, dateTimeTo);
//
//                        listContainer.addToList(eventTask);
//
//                        printResult(inputCommand, eventTask);
//                        break;
//                    }
//
//                    default:
//                        throw new DukeException(UNKNOWN_COMMAND);
//                }
//
//                System.out.println(SEPARATOR_LINE);
//                saveChanges();
//            } catch (DukeException e) {
//                System.out.println(e.printError());
//            } catch (Exception e) {
//                System.out.println("some other exception " + e.getMessage());
//            }
////            System.out.println(inputString);
//
//
//        }


    }



    /**
     * Prints feedback to the user on what and how a Task got modified,
     * based on the user's command.
     *
     * @param command
     */
//    private static void printResult(Commands command, Task task) {
//        switch (command) {
//            case TODO:
//            case DEADLINE:
//            case EVENT: {
//                System.out.println("\uD83D\uDE0A I've added a new task: " + task.toString());
//                System.out.println("Now you have " + listContainer.getSize() + " tasks!");
//                break;
//            }
//            case MARK: {
//                System.out.println("Nice! I've marked this task as done: \n    " + task.toString());
//                break;
//            }
//            case UNMARK: {
//                System.out.println("Nice! I've marked this task as undone: \n    " + task.toString());
//                break;
//            }
//            case DELETE: {
//                System.out.println("\uD83D\uDE0A I've removed this task: " + task.toString());
//                break;
//            }
//            case LIST: {
//                System.out.println(listContainer);
//                break;
//            }
//            case BYE: {
//                String exitMsg = "Bye! Hope to see you again soon.";
//                System.out.println(exitMsg);
//                System.out.println(SEPARATOR_LINE);
//                break;
//            }
//        }
//    }

    /**
     * Saves changes in a file.
     */
//    private static void saveChanges() {
//        String serialized = listContainer.serialize();
//
//        try {
//            // create file if not exists
//
//
//            // write to file
//            FileWriter fileWriter = new FileWriter(String.valueOf(SAVE_FILE_LOCATION));
//            fileWriter.write(serialized);
//            fileWriter.close();
//
//        } catch (IOException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//
//
//    }


}

