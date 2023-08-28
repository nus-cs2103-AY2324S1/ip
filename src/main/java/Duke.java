import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String INDENTATION = "    ";
    private static ArrayList<Task> store = new ArrayList<Task>();

    private static Storage storage;
    private TaskList tasks;
    private static final String FILEPATH = "./data/duke.txt";

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }

    private static void checkCommandArguments(String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException("Hey, the description of a " + commandArr[0] + " cannot be empty!");
        }
    }

    private enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private String value;

        Command(String value) {
            this.value = value;
        }

        public static Command getCommand(String value) {
            for (Command cmd : Command.values()) {
                if (cmd.value.equals(value)) {
                    return cmd;
                }
            }
            return null;
        }
    }

    private void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String commandString;
        Command command;
        String[] commandArray;

        while (true) {
            commandString = sc.nextLine();

            if (commandString.length() == 0) {
                System.out.println(formatOutput("Hey, Type Something!"));
                continue;
            }

            commandArray = commandString.split(" ", 2);
            command = Command.getCommand(commandArray[0]);

            if (command == null) {
                System.out.println(formatOutput("I don't understand what you're saying."));
                continue;
            }

            try {
                switch (command) {
                    case BYE:
                        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
                        return;
                    case LIST:
                        System.out.println(formatOutput(tasks.formatList()));
                        break;
                    case MARK:
                    case UNMARK:
                        checkCommandArguments(commandArray);
                        tasks.handleMarking(commandArray[1], command.value);
                        break;
                    case DELETE:
                        checkCommandArguments(commandArray);
                        tasks.handleDelete(commandArray[1]);
                        break;
                    case TODO:
                        checkCommandArguments(commandArray);
                        tasks.handleToDo(commandArray[1]);
                        break;
                    case DEADLINE:
                        checkCommandArguments(commandArray);
                        tasks.handleDeadline(commandArray[1]);
                        break;
                    case EVENT:
                        checkCommandArguments(commandArray);
                        tasks.handleEvent(commandArray[1]);
                        break;
                    default:
                        System.out.println(formatOutput("I don't understand what you're saying."));
                        break;
                }
            } catch (DukeException e) {
                System.out.println(formatOutput(e.getMessage()));
            }
        }

    }

    public void run() {
        storage = new Storage(FILEPATH);

        System.out.println(formatOutput("Hello! I'm Nano\n" + INDENTATION + " What can I do for you?"));
        try {
            tasks = new TaskList(storage.load(), storage);

        } catch (DukeException e) {
            System.out.println("--- No Data Stored ---");
            tasks = new TaskList(new ArrayList<>(), storage);
        }
        handleCommand();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
