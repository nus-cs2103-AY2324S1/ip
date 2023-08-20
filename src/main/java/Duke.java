import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke.botPrintBr();
        Duke.botPrintMessage("Greetings user, I'm Didier. How can I help you?");
        Duke.botPrintBr();

        Scanner scanner = new Scanner(System.in); // create a Scanner object for user input
        String[] command = scanner.nextLine().split(" ", 2);
        ArrayList<Task> tasks = new ArrayList<>();
        while (!command[0].equals("bye")) {
            // Carry out the action determined by the command
            switch (command[0]) {
                case "list":
                    Duke.botPrintMessage("The tasks in your list are as follows:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Duke.botPrintMessage(String.format("%d.%s", i + 1, tasks.get(i)));
                    }
                    break;
                case "mark":
                case "unmark":
                    try {
                        Task taskMark = tasks.get(Integer.parseInt(command[1]) - 1);
                        if (command[0].equals("mark")) {
                            taskMark.markAsDone();
                            Duke.botPrintMessage("Okay! I've marked the following task as done:");
                        } else {
                            taskMark.markAsNotDone();
                            Duke.botPrintMessage("Okay! I've marked the following task as undone:");
                        }
                        Duke.botPrintMessage(taskMark.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        Duke.botPrintMessage("That is an invalid task number");
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    Task task;
                    if (command.length == 1 || command[1].isBlank()) {
                        Duke.botPrintMessage("The description of the task is missing. Please try again.");
                        break;
                    }
                    if (command[0].equals("todo")) {
                        task = new ToDo(command[1]);
                    } else if (command[0].equals("deadline")) {
                        String[] deadlineCommand = command[1].split("\\\\by ", 2);
                        if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                            Duke.botPrintMessage("The deadline of the task is missing. Please try again.");
                            break;
                        } else {
                            task = new Deadline(deadlineCommand[0], deadlineCommand[1]);
                        }
                    } else {
                        String[] fromCommand = command[1].split("\\\\from ", 2);
                        if (fromCommand.length == 1) {
                            Duke.botPrintMessage("The start time of the task is missing. Please try again.");
                            break;
                        }
                        String[] toCommand = fromCommand[1].split("\\\\to ", 2);
                        if (toCommand[0].isBlank()) {
                            Duke.botPrintMessage("The start time of the task is missing. Please try again.");
                            break;
                        } else if (toCommand.length == 1 || toCommand[1].isBlank()) {
                            Duke.botPrintMessage("The end time of the task is missing. Please try again.");
                            break;
                        } else {
                            task = new Event(fromCommand[0], toCommand[0], toCommand[1]);
                        }
                    }
                    if (task != null) {
                        tasks.add(task);
                        Duke.botPrintMessage("Okay! I've added your task:");
                        Duke.botPrintMessage(task.toString());
                        Duke.botPrintMessage(String.format("There are now %d tasks in your list", tasks.size()));
                    }
                    break;
                default:
                    Duke.botPrintMessage("I don't quite understand you. Could you repeat?");
            }
            Duke.botPrintBr();
            command = scanner.nextLine().split(" ", 2);
        }

        Duke.botPrintMessage("Goodbye! If you need more help in the future don't hesitate to ask me.");
        Duke.botPrintBr();
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
        Duke.botPrintMessage(lineBreak);
    }

}
