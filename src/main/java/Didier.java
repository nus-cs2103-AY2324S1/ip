import java.util.ArrayList;
import java.util.Scanner;

public class Didier {

    public static void main(String[] args) {
        Didier.botPrintBr();
        Didier.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Didier.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String[] command = scanner.nextLine().split(" ", 2);
        ArrayList<Task> tasks = new ArrayList<>();
        while (!command[0].equals("bye")) {
            // Carry out the action determined by the command
            try {
                switch (command[0]) {
                    case "list":
                        Didier.botPrintMessage("The tasks in your list are as follows:");
                        for (int i = 0; i < tasks.size(); i++) {
                            Didier.botPrintMessage(String.format("%d.%s", i + 1, tasks.get(i)));
                        }
                        break;
                    case "mark":
                    case "unmark":
                        try {
                            Task taskMark = tasks.get(Integer.parseInt(command[1]) - 1);
                            if (command[0].equals("mark")) {
                                taskMark.markAsDone();
                                Didier.botPrintMessage("Okay! I've marked the following task as done:");
                            } else {
                                taskMark.markAsNotDone();
                                Didier.botPrintMessage("Okay! I've marked the following task as undone:");
                            }
                            Didier.botPrintMessage(taskMark.toString());
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            throw new TaskNumberException(command[1]);
                        }
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task task;
                        if (command.length == 1 || command[1].isBlank()) {
                            throw new ElementMissingException("description");
                        }
                        if (command[0].equals("todo")) {
                            task = new ToDo(command[1]);
                        } else if (command[0].equals("deadline")) {
                            String[] deadlineCommand = command[1].split("\\\\by ", 2);
                            if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                                throw new ElementMissingException("deadline");
                            } else {
                                task = new Deadline(deadlineCommand[0], deadlineCommand[1]);
                            }
                        } else {
                            String[] fromCommand = command[1].split("\\\\from ", 2);
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
                    default:
                        throw new InvalidCommandException(command[0]);
                }
            } catch (DidierException e) {
                Didier.botPrintMessage(e.getMessage() + "Please try again.");
            }
            Didier.botPrintBr();
            command = scanner.nextLine().split(" ", 2);
        }

        Didier.botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        Didier.botPrintBr();
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
