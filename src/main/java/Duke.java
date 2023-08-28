import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String INDENTATION = "    ";
    private static ArrayList<Task> store = new ArrayList<Task>();

    private static Storage storage;
    private TaskList tasks;
    private static final String FILEPATH = "./data/duke.txt";

    private static String formatOutput(String output) {
        String horizontalLine = "____________________________________________________________";
        return INDENTATION + horizontalLine + "\n " +
                INDENTATION + output + '\n' + INDENTATION + horizontalLine + '\n';
    }


    private void handleCommand() {
        Scanner sc = new Scanner(System.in);
        String commandString;
        Command command;

        while (true) {
            commandString = sc.nextLine();

            Parser parseLine = new Parser(commandString);
            command = parseLine.getCommand();

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
                        tasks.handleMarking(parseLine.getArguments(), command.getCommandName());
                        break;
                    case DELETE:
                        tasks.handleDelete(parseLine.getArguments());
                        break;
                    case TODO:
                        String todoData = parseLine.parseToDoArguments();
                        tasks.handleToDo(todoData);
                        break;
                    case DEADLINE:
                        String[] deadlineData = parseLine.parseDeadlineArguments();
                        tasks.handleDeadline(deadlineData[0], deadlineData[1]);
                        break;
                    case EVENT:
                        String[] eventData = parseLine.parseEventArguments();
                        tasks.handleEvent(eventData[0], eventData[1], eventData[2]);
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
