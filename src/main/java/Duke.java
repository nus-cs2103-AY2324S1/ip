import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private final static String CHATBOT_NAME = "Fluke";
    private final static String LOGO =
            "    ________      __\n" +
                    "   / ____/ /_  __/ /_____\n" +
                    "  / /_  / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__,_/_/|_|\\___/";
    private final static String SAVE_FILE_NAME = "fluke.txt";
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    }

    public static void main(String[] args) {
        // introduce Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        greet();
        addHorizontalLine();
        // initialise scanner to read file
        File saveFile = new File(SAVE_FILE_NAME);
        if (saveFile.exists()) {
            // parse the file and write to list
            try {
                Scanner fileScanner = new Scanner(saveFile);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    if (!line.equals("")) {
                        Task task = parseTask(line);
                        listOfTasks.add(task);
                    }
                }

            } catch (DukeException d) {
                printError(d.getMessage());
            } catch (FileNotFoundException f) {
                printError("Oh no!");
            }
        }
        // initialise scanner to check for user input
        Scanner scanner = new Scanner(System.in);
        boolean waitingForInput = true;
        while (waitingForInput) {
            // check for user commands
            try {
                String nextCommand = scanner.nextLine();
                Command commandType = parseCommand(nextCommand);
                switch (commandType) {
                case BYE:
                    waitingForInput = false;
                    sayBye();
                    break;
                case LIST:
                    list();
                    break;
                case MARK:
                case UNMARK:
                case DELETE:
                case TODO:
                case DEADLINE:
                case EVENT:
                    changeTodoList(commandType, nextCommand);
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (DukeException d) {
                printError(d.getMessage());
            }
            addHorizontalLine();
        }
    }

    private static Command parseCommand(String nextCommand) throws InvalidInputException {
        if (nextCommand.equals("bye")) {
            return Command.BYE;
        } else if (nextCommand.equals("list")) {
            return Command.LIST;
        } else if (nextCommand.startsWith("mark")) {
            return Command.MARK;
        } else if (nextCommand.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (nextCommand.startsWith("delete")) {
            return Command.DELETE;
        } else if (nextCommand.startsWith("todo")) {
            return Command.TODO;
        } else if (nextCommand.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (nextCommand.startsWith("event")) {
            return Command.EVENT;
        } else {
            throw new InvalidInputException();
        }
    }

    private static void greet() {
        System.out.println(
                "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                        "Feeling lucky today?"
        );
    }

    private static void sayBye() {
        System.out.println("Bye. Good luck!");
    }

    private static void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void addTask(Task task) {
        System.out.println("(Scribbles randomly). Hope I got it right!");
        System.out.println("  " + task);
        listOfTasks.add(task);
        System.out.println("I think there are now " + listOfTasks.size() + " tasks in the list.");
    }

    private static void addTodo(String command) {
        try {
            if (command.length() <= 5) {
                // command is too short, description is invalid
                throw new EmptyDescriptionException();
            }
            String str = command.substring(5);
            Todo newTodo = new Todo(str);
            addTask(newTodo);
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static void addDeadline(String command) {
        try {
            if (command.length() <= 9) {
                // command is too short, description is invalid
                throw new EmptyDescriptionException();
            }
            String str = command.substring(9);
            int byIndex = str.indexOf("/by");
            if (byIndex < 0) {
                throw new InvalidInputException();
            }
            String description = str.substring(0, byIndex - 1);
            String by = str.substring(byIndex + 4);
            Task task = new Deadline(description, by);
            addTask(task);
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static void addEvent(String command) {
        try {
            if (command.length() <= 6) {
                // command is too short, description is invalid
                throw new EmptyDescriptionException();
            }
            String str = command.substring(6);
            int fromIndex = str.indexOf("/from");
            int toIndex = str.indexOf("/to");
            if (fromIndex < 0 || toIndex < 0) {
                throw new InvalidInputException();
            }
            String description = str.substring(0, fromIndex - 1);
            String from = str.substring(fromIndex + 6, toIndex - 1);
            String to = str.substring(toIndex + 4);
            Task task = new Event(description, from, to);
            addTask(task);
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static void list() {
        System.out.println("Here are the tasks we have currently!");
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            int number = i + 1;
            System.out.println(number + "." + task);
        }
    }

    private static void markTaskAsDone(String nextCommand) {
        try {
            if (nextCommand.length() <= 5) {
                throw new InvalidInputException();
            }
            int taskNumber = obtainTaskNumber(nextCommand.substring(5));
            int index = taskNumber - 1;
            // check if task exists
            if (index < listOfTasks.size()) {
                listOfTasks.get(index).markAsDone();
                System.out.println("I have marked this task as done, I hope it's the right one:");
                System.out.println("  " + listOfTasks.get(index));
            } else {
                throw new TaskDoesNotExistException();
            }
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static void markTaskAsUndone(String nextCommand) {
        try {
            if (nextCommand.length() <= 7) {
                throw new InvalidInputException();
            }
            int taskNumber = obtainTaskNumber(nextCommand.substring(7));
            int index = taskNumber - 1;
            // check if task exists
            if (index < listOfTasks.size()) {
                listOfTasks.get(index).markAsUndone();
                System.out.println("  " + "I have marked this task as not done yet, I hope it's the right one:");
                System.out.println(listOfTasks.get(index));
            } else {
                throw new TaskDoesNotExistException();
            }
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static void deleteTask(String nextCommand) {
        try {
            if (nextCommand.length() <= 7) {
                throw new InvalidInputException();
            }
            int taskNumber = obtainTaskNumber(nextCommand.substring(7));
            int index = taskNumber - 1;
            // check if task exists
            if (index < listOfTasks.size()) {
                String taskString = listOfTasks.get(index).toString();
                listOfTasks.remove(index);
                System.out.println("Task deleted! I hope it's the right one:");
                System.out.println("  " + taskString);
                System.out.println("I think there are now " + listOfTasks.size() + " tasks in the list.");
            } else {
                throw new TaskDoesNotExistException();
            }
        } catch (DukeException d) {
            printError(d.getMessage());
        }
    }

    private static int obtainTaskNumber(String taskNumberString) throws InvalidInputException {
        try {
            return Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }

    private static void printError(String message) {
        System.out.println("☹ OOPS!!! " + message);
    }

    /**
     * Helper function for additional logic related to changing the list.
     * @param commandType type of command
     * @param nextCommand the content in the command
     */
    private static void changeTodoList(Command commandType, String nextCommand) {
        // 1. make changes to the list
        switch (commandType) {
        case MARK:
            markTaskAsDone(nextCommand);
            break;
        case UNMARK:
            markTaskAsUndone(nextCommand);
            break;
        case DELETE:
            deleteTask(nextCommand);
            break;
        case TODO:
            addTodo(nextCommand);
            break;
        case DEADLINE:
            addDeadline(nextCommand);
            break;
        case EVENT:
            addEvent(nextCommand);
            break;
        default:
            // should not occur
            printError("An unknown error has occurred.");
        }
        // 2. save to file
        try {
            save();
        } catch (DukeException e) {
            printError(e.getMessage());
        }
    }

    private static void save() throws DukeException {
        try {
            FileWriter writer = new FileWriter("fluke.txt");
            for (int i = 0; i < listOfTasks.size(); i++) {
                String out = listOfTasks.get(i).toString() + "\n";
                writer.write(out);
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static Task parseTask(String taskString) throws DukeException {
        Command taskType;
        boolean isMarked;
        // parse type
        Pattern typePattern = Pattern.compile("\\[[TDE]]");
        Matcher typeMatcher = typePattern.matcher(taskString);
        boolean typeFound = typeMatcher.find();
        if (!typeFound) {
            throw new SaveFileParsingException();
        }
        switch (typeMatcher.group()) {
        case "[T]":
            taskType = Command.TODO;
            break;
        case "[D]":
            taskType = Command.DEADLINE;
            break;
        case "[E]":
            taskType = Command.EVENT;
            break;
        default:
            throw new SaveFileParsingException();
        }

        // parse mark
        Pattern markPattern = Pattern.compile("\\[[X ]]");
        Matcher markMatcher = markPattern.matcher(taskString);
        boolean markFound = markMatcher.find();
        if (!markFound) {
            throw new SaveFileParsingException();
        }
        String mark = markMatcher.group();
        switch (mark) {
        case "[ ]":
            isMarked = false;
            break;
        case "[X]":
            isMarked = true;
            break;
        default:
            throw new SaveFileParsingException();
        }

        String taskDesc = taskString.substring(7);
        if (taskType == Command.TODO) {
            return new Todo(taskDesc, isMarked);
        } else if (taskType == Command.DEADLINE) {
            // parse by date
            int bracketStartIndex = taskDesc.indexOf('(');
            int bracketEndIndex = taskDesc.indexOf(')');
            if (bracketStartIndex < 0 || bracketEndIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            String by = taskDesc.substring(bracketStartIndex + 4, bracketEndIndex).trim();
            return new Deadline(desc, isMarked, by);
        } else if (taskType == Command.EVENT) {
            int bracketStartIndex = taskDesc.indexOf('(');
            if (bracketStartIndex < 0) {
                throw new SaveFileParsingException();
            }
            String desc = taskDesc.substring(0, bracketStartIndex).trim();
            // parse from date
            Pattern fromPattern = Pattern.compile("from:.+to:");
            Matcher fromMatcher = fromPattern.matcher(taskDesc);
            boolean fromFound = fromMatcher.find();
            if (!fromFound) {
                throw new SaveFileParsingException();
            }
            String from = fromMatcher.group().replaceFirst("to:", "").substring(5).trim();
            // parse to date
            Pattern toPattern = Pattern.compile("to:[\\S\\s]+");
            Matcher toMatcher = toPattern.matcher(taskDesc);
            boolean toFound = toMatcher.find();
            if (!toFound) {
                throw new SaveFileParsingException();
            }
            String to = toMatcher.group().substring(3).trim();
            return new Event(desc, isMarked, from, to);
        }
        throw new SaveFileParsingException();
    }
}
