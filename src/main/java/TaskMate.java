import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TaskMate {

    static String horizontalLine = "--------------------";
    static String chatbotName = "TaskMate";
    static String defaultSaveTaskFilePath = "./data/saved_tasks.txt";

    enum CommandTypes {
        list, bye, todo, deadline, event, mark, unmark, delete
    }

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    TaskMate(String filePath) {
        this.ui = new Ui(chatbotName);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();

        // Load existing tasks from disk
        try {
            String fileContents = storage.readFromFile();
            this.tasks = new TaskList(fileContents);
        } catch (IOException e) {
            ui.printFileNotFoundResponse(storage.getSaveFilePath());
        } catch (NoDataException e) {
            ui.printNoDataResponse();
        }
    }

    TaskMate() {
        this.ui = new Ui(chatbotName);
        this.storage = new Storage(defaultSaveTaskFilePath);
        this.tasks = new TaskList();

        // Load existing tasks from disk
        try {
            String fileContents = storage.readFromFile();
            this.tasks = new TaskList(fileContents);
        } catch (IOException e) {
            ui.printFileNotFoundResponse(storage.getSaveFilePath());
        } catch (NoDataException e) {
            ui.printNoDataResponse();
        }
    }

    public static void main(String[] args) {
        new TaskMate().run();
    }

    public void run() {

        // Greets user
        ui.greetUser();

        // Reading user input
        // todo
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();

            // Checks if user input is correct (Error handling)
            checkInvalidCommandTypeException(userInput);

            // exits
            if (getCommandType(userInput).equals(CommandTypes.bye.toString())) {
                break;
            // list
            } else if (getCommandType(userInput).equals(CommandTypes.list.toString())) {
                processListCommand();
            // Add task OR mark/unmark
            } else if (getCommandType(userInput).equals(CommandTypes.mark.toString()) | getCommandType(userInput).equals(CommandTypes.unmark.toString())) {
                try {
                    checkValidMarkOrUnmarkCommand(userInput);
                } catch (InvalidCommandTypeException e) {
                    ui.printInvalidCommandTypeExceptionResponse();
                    System.exit(0);
                } catch (InvalidDescriptionException e) {
                    ui.printInvalidMarkOrUnmarkResponse(tasks.getNumTotalTasks());
                    System.exit(0);
                }
                processMarkUnmarkCommand(userInput);
            } else if (getCommandType(userInput).equals(CommandTypes.todo.toString())) {
                try {
                    checkValidTodoCommand(userInput);
                } catch (InvalidCommandTypeException e) {
                    ui.printInvalidCommandTypeExceptionResponse();
                    System.exit(0);
                } catch (InvalidDescriptionException e) {
                    ui.printEmptyTodoDescriptionResponse();
                    System.exit(0);
                }
                processAddTaskCommand(userInput);
            } else if (getCommandType(userInput).equals(CommandTypes.deadline.toString())) {
                // TODO: Add checks for Deadline-type tasks
                processAddTaskCommand(userInput);
            } else if (getCommandType(userInput).equals(CommandTypes.event.toString())) {
                // TODO: Add checks for Event-type tasks
                processAddTaskCommand(userInput);
            } else if (getCommandType(userInput).equals(CommandTypes.delete.toString())) {
                try {
                    checkValidDeleteCommand(userInput);
                } catch (InvalidCommandTypeException e) {
                    ui.printInvalidCommandTypeExceptionResponse();
                    System.exit(0);
                } catch (InvalidDescriptionException e) {
                    ui.printInvalidDeleteResponse(tasks.getNumTotalTasks());
                    System.exit(0);
                }
                processDeleteCommand(userInput);
            }
        }


        // Exit procedure
        // 1. Write incomplete tasks to disk
        String saveTaskText = tasks.formatAllTasksForSaving();
        System.out.println(saveTaskText);
        try {
            storage.writeToFile(saveTaskText);
        } catch (IOException e) {
            ui.printSaveFailResponse(System.getProperty("user.dir") +
                    defaultSaveTaskFilePath.substring(1).replace("/", "\\"));
        }
        // 2. Print exit message
        ui.farewellUser();
    }

    static void printReply(String text) {
        // prints text with horizontal lines above and below it
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }

    void checkValidMarkOrUnmarkCommand(String userInput) throws InvalidCommandTypeException, InvalidDescriptionException {
        // Checks if the user input command is a valid "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace,
        // followed by an integer from 1 to Task.getAllTasks().size()
        String indexWithinList;

        if (userInput.startsWith(CommandTypes.mark.toString())) {
            indexWithinList = userInput.substring(CommandTypes.mark.toString().length()).trim();
        } else {
            if (!userInput.startsWith(CommandTypes.unmark.toString())) {
                throw new InvalidCommandTypeException();
            }
            indexWithinList = userInput.substring(CommandTypes.unmark.toString().length()).trim();
        }

        if (!checkStringIsInteger(indexWithinList)) {
            throw new InvalidDescriptionException();
        } else if (Integer.parseInt(indexWithinList) < 1 | Integer.parseInt(indexWithinList) > tasks.getAllTasks().size()) {
            throw new InvalidDescriptionException();
        }
    }

    static void checkValidTodoCommand(String userInput) throws InvalidCommandTypeException, InvalidDescriptionException {
        // Checks if "todo" command is valid by checking if there is text
        // coming after the word "todo"
        if (!userInput.startsWith(CommandTypes.todo.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(CommandTypes.todo.toString().length()).isEmpty()) {
            throw new InvalidDescriptionException();
        }
    }

    void checkValidDeleteCommand(String userInput) throws InvalidCommandTypeException, InvalidDescriptionException {
        // Checks if the user input command is a valid "delete" command
        // by checking if the command starts with "delete", followed by a whitespace,
        // followed by an integer from 1 to Task.getAllTasks().size()
        String indexWithinList;

        if (userInput.startsWith(CommandTypes.delete.toString())) {
            indexWithinList = userInput.substring(CommandTypes.delete.toString().length()).trim();
        } else {
            throw new InvalidCommandTypeException();
        }

        if (!checkStringIsInteger(indexWithinList)) {
            throw new InvalidDescriptionException();
        } else if (Integer.parseInt(indexWithinList) < 1 | Integer.parseInt(indexWithinList) > tasks.getAllTasks().size()) {
            throw new InvalidDescriptionException();
        }
    }

    static boolean checkStringIsInteger(String s) {
        // Returns true if s can be parsed into an Integer object, and false otherwise
        try {
            Integer i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static String getCommandType(String userInput) {
        // Returns the type of command input by the user
        // Possible values: "todo", "deadline", "event", "bye", "list", "mark", "unmark"
        for (CommandTypes type : CommandTypes.values()) {
            String typeString = type.toString();
            if (userInput.startsWith(typeString)) {
                return typeString;
            }
        }
        System.out.println("INVALID COMMAND TYPE: " + userInput);
        return ""; // todo
    }

    static void checkInvalidCommandTypeException(String userInput) {
        String commandType = getCommandType(userInput);
        if (commandType.isEmpty()) {
            printReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.exit(0);
        }
    }

    void processListCommand() {
        // EDIT
        String allTasksString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            Task newTask = tasks.getAllTasks().get(i);
            allTasksString += (i + 1) + "." + newTask.toString() + "\n";
        }
        printReply(allTasksString);
    }

    void processMarkUnmarkCommand(String userInput) {
        if (userInput.startsWith("mark")) {
            int indexToMark = Integer.parseInt(userInput.substring(CommandTypes.mark.toString().length()).trim());
            indexToMark -= 1;
            Task taskToMark = tasks.getAllTasks().get(indexToMark);
            taskToMark.markAsDone();

            // print message when marking as done
            String message = "Nice! I've marked this task as done:\n  " + taskToMark;
            printReply(message);

        } else {
            int indexToUnmark = Integer.parseInt(userInput.substring(CommandTypes.unmark.toString().length()).trim());
            indexToUnmark -= 1;
            Task taskToUnmark = tasks.getAllTasks().get(indexToUnmark);
            taskToUnmark.markAsNotDone();

            // print message when unmarking as done
            String message = "[ ] " + taskToUnmark;
            message = "OK, I've marked this task as not done yet:\n" + message;
            printReply(message);
        }
    }

    void processAddTaskCommand(String userInput) {
        Task newTask;
        if (userInput.startsWith("todo ")) {
            newTask = new Todo(userInput.substring(CommandTypes.todo.toString().length()+1)); // +1 because we do not want the task name to start from the space character
        } else if (userInput.startsWith("deadline ")) {
            userInput = userInput.substring(CommandTypes.deadline.toString().length()+1); // +1 because we do not want the task name to start from the space character
            String[] splitUserInput = userInput.split(" /");
            newTask = new Deadline(
                    splitUserInput[0],
                    splitUserInput[1].replace("by ", "")
            );
        } else {
            userInput = userInput.substring(CommandTypes.event.toString().length()+1); // +1 because we do not want the task name to start from the space character
            String[] splitUserInput = userInput.split(" /");
            newTask = new Event(
                    splitUserInput[0],
                    splitUserInput[1].replace("from ", ""),
                    splitUserInput[2].replace("to ", "")
            );
        }
        tasks.addTask(newTask);
        printReply("Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.getAllTasks().size() + " task(s) in the list.");
    }

    void processDeleteCommand(String userInput) {
        int indexToDelete = Integer.parseInt(userInput.substring(CommandTypes.delete.toString().length()).trim());
        indexToDelete -= 1; // subtract 1 as the arraylist is zero-indexed
        Task removedTask = tasks.removeTask(indexToDelete);
        printReply("Noted. I've removed this task:\n  " + removedTask.toString() + "\nNow you have " + tasks.getAllTasks().size() + " task(s) in the list.");
    }

}