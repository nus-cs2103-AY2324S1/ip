import java.util.Scanner;


/**
 * Monday is a task management ChatBot. Users can add, mark, unmark,
 * delete, keep track of the tasks they have.
 */
public class Monday {
    private static TaskList TaskList;

    /**
     * An enumeration of available commands.
     */
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    /**
     * Starts Monday application.
     * Initialises necessary components, greets the user, handle user input and handle errors.
     */
    private static void startMonday(String filepath) {
        Scanner scanner = new Scanner(System.in);
        TaskList = new TaskList(filepath);
        boolean running = true;

        printSeparator();
        greet();
        printSeparator();
        while (running) {
            String userInput = scanner.nextLine();
            printSeparator();
            try {
                running = mondayParser(userInput);
            } catch (MondayExceptions e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Mark/UnMark number error. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of Bound Error: " + e.getMessage());
            }
            printSeparator();
        }
        scanner.close();
    }

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param userInput the user input to be parsed
     * @return true if the application should continue running, false otherwise
     * @throws MondayExceptions if there are errors related to Monday application
     * @throws IllegalArgumentException if user input is in the wrong format
     */
    private static boolean mondayParser(String userInput) throws MondayExceptions {
        String[] input = userInput.split(" ", 2);
        String command = input[0];
        String content = input.length > 1 ? input[1] : null;

        Command commandEnum = Command.valueOf(command.toUpperCase());


        switch (commandEnum) {
            case BYE:
                exit();
                return false;
            case LIST:
                TaskList.displayList();
                break;
            case MARK: {
                if (content == null) {
                    throw new MondayExceptions("Mark requires a index to mark the task as completed.");
                }
                int index = Integer.parseInt(content);

                TaskList.mark(index);
                break;
            }
            case UNMARK: {
                if (content == null) {
                    throw new MondayExceptions("UnMark requires a index to mark the task as uncompleted.");
                }

                int index = Integer.parseInt(content);

                TaskList.unMark(index);
                break;
            }
            case TODO:
                if (content == null) {
                    throw new MondayExceptions("The description of a todo cannot be empty.\n" +
                            "Usage: todo (task)");
                }

                TaskList.addToTask(new ToDos(content));
                break;
            case DEADLINE:
                try {
                    if (content == null) {
                        throw new MondayExceptions("The description of a deadline cannot be empty.\n" +
                                "Usage: deadline (task) /by (time)");
                    }

                    String[] taskDetails = content.split("/by",2);
                    String description = taskDetails[0];
                    String date = taskDetails[1];

                    TaskList.addToTask(new Deadlines(description.trim(), date.trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid Format. " +
                            "Usage: deadline (task) /by (time)");
                }
                break;
            case EVENT:
                try {
                    if (content == null) {
                        throw new MondayExceptions("The description of a event cannot be empty.\n" +
                                "Usage: event (task) /from (start time) /to (end time)");
                    }

                    String[] taskDetails = content.split("/from", 2);
                    String description = taskDetails[0];
                    String[] taskTiming = taskDetails[1].split("/to", 2);
                    String start = taskTiming[0];
                    String end = taskTiming[1];

                    TaskList.addToTask(new Events(description.trim(),
                            start.trim(),
                            end.trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid Format. " +
                            "Usage: event (task) /from (start time) /to (end time)");
                }
                break;
            case DELETE:
                if (content == null) {
                    throw new MondayExceptions("Delete requires a index to delete the task");
                }
                int index = Integer.parseInt(content);

                TaskList.delete(index);
                break;
            default:
                throw new MondayExceptions("Sorry, I do not understand what that means.\n" +
                        "Please provide a valid input/command. e.g todo read book");
        }
        return true;
    }

    /**
     * Prints a greeting message to the console.
     */
    private static void greet() {
        System.out.println("Hello! I'm " + "Monday");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a farewell message to the console.
     */
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a separator line to the console.
     */
    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Entry point to Monday.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        startMonday("./data/duke.txt");
    }
}
