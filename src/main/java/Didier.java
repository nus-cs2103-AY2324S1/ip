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
                        Didier.botPrintMessage("That is an invalid task number");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Task task;
                    if (command.length == 1 || command[1].isBlank()) {
                        Didier.botPrintMessage("The description of the task is missing. Please try again.");
                        break;
                    }
                    if (command[0].equals("todo")) {
                        task = new ToDo(command[1]);
                    } else if (command[0].equals("deadline")) {
                        String[] deadlineCommand = command[1].split("\\\\by ", 2);
                        if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                            Didier.botPrintMessage("The deadline of the task is missing. Please try again.");
                            break;
                        } else {
                            task = new Deadline(deadlineCommand[0], deadlineCommand[1]);
                        }
                    } else {
                        String[] fromCommand = command[1].split("\\\\from ", 2);
                        if (fromCommand.length == 1) {
                            Didier.botPrintMessage("The start time of the task is missing. Please try again.");
                            break;
                        }
                        String[] toCommand = fromCommand[1].split("\\\\to ", 2);
                        if (toCommand[0].isBlank()) {
                            Didier.botPrintMessage("The start time of the task is missing. Please try again.");
                            break;
                        } else if (toCommand.length == 1 || toCommand[1].isBlank()) {
                            Didier.botPrintMessage("The end time of the task is missing. Please try again.");
                            break;
                        } else {
                            task = new Event(fromCommand[0], toCommand[0], toCommand[1]);
                        }
                    }
                    if (task != null) {
                        tasks.add(task);
                        Didier.botPrintMessage("Okay! I've added your task:");
                        Didier.botPrintMessage(task.toString());
                        Didier.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    }
                    break;
                default:
                    Didier.botPrintMessage("I don't quite understand you. Could you repeat?");
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
