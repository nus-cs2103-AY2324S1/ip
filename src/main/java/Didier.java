import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Didier {
    enum Command {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        UNKOWN;

        static Command textToCommand(String text) {
            try {
                Command command = Command.valueOf(text.toUpperCase());
                return command;
            } catch (IllegalArgumentException e) {
                return Command.UNKOWN;
            }
        }
    }

    public static void main(String[] args) {
        Didier.botPrintBr();
        Didier.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Didier.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String[] userInput = scanner.nextLine().split(" ", 2);
        Command command = Command.textToCommand(userInput[0]);
        ArrayList<Task> tasks = new ArrayList<>();
        while (!command.equals(Command.BYE)) {
            // Carry out the action determined by the command
            try {
                switch (command) {
                case LIST:
                    Didier.botPrintMessage("The tasks in your list are as follows:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Didier.botPrintMessage(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                    break;
                case MARK:
                case UNMARK:
                    try {
                        Task task = tasks.get(Integer.parseInt(userInput[1]) - 1);
                        if (userInput[0].equals("mark")) {
                            task.markAsDone();
                            Didier.botPrintMessage("Okay! I've marked the following task as done:");
                        } else {
                            task.markAsNotDone();
                            Didier.botPrintMessage("Okay! I've marked the following task as undone:");
                        }
                        Didier.botPrintMessage(task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new TaskNumberException(userInput[1]);
                    }
                    break;
                case DELETE:
                    try {
                        Task task = tasks.remove(Integer.parseInt(userInput[1]) - 1);
                        Didier.botPrintMessage("Okay! I've removed this task:");
                        Didier.botPrintMessage(task.toString());
                        Didier.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new TaskNumberException(userInput[1]);
                    }
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task task;
                    if (userInput.length == 1 || userInput[1].isBlank()) {
                        throw new ElementMissingException("description");
                    }
                    if (userInput[0].equals("todo")) {
                        task = new ToDo(userInput[1]);
                    } else if (userInput[0].equals("deadline")) {
                        String[] deadlineCommand = userInput[1].split("\\\\by ", 2);
                        if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                            throw new ElementMissingException("deadline");
                        } else {
                            task = new Deadline(deadlineCommand[0], deadlineCommand[1]);
                        }
                    } else {
                        String[] fromCommand = userInput[1].split("\\\\from ", 2);
                        if (fromCommand.length == 1 || fromCommand[1].isBlank()) {
                            throw new ElementMissingException("start time");
                        }
                        String[] toCommand = fromCommand[1].split("\\\\to ", 2);
                        if (toCommand.length == 1 || toCommand[1].isBlank()) {
                            throw new ElementMissingException("end time");
                        } else {
                            task = new Event(fromCommand[0], toCommand[0], toCommand[1]);
                        }
                    }
                    tasks.add(task);
                    Didier.botPrintMessage("Okay! I've added your task:");
                    Didier.botPrintMessage(task.toString());
                    Didier.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    break;
                case UNKOWN:
                    throw new InvalidCommandException(userInput[0]);
                }
            } catch (DidierException e) {
                Didier.botPrintMessage(e.getMessage() + "Please try again.");
            }
            Didier.botPrintBr();
            userInput = scanner.nextLine().split(" ", 2);
            command = Command.textToCommand(userInput[0]);
        }

        Didier.botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        Didier.botPrintBr();
        scanner.close();
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    private static void botPrintMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints a horizontal line break to the standard output.
     */
    private static void botPrintBr() {
        String lineBreak = "---------------------------------------------------------------------";
        Didier.botPrintMessage(lineBreak);
    }

}
