import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    private final Save savior = new Save();

    public Duke() {
        ArrayList<Task> loadedTasks = savior.loadTasks();
        actionList.add(loadedTasks);
    }

    public void initiateChatbot() {
        ui.openingMessage();
        String input = ui.readInput();
        Parser.Command command = parser.getCommand(input);
        while (command != Parser.Command.BYE) {
            try {
                String[] inputParts = parser.splitInput(input);
                switch (command) {
                case LIST:
                    ui.lineSandwich(actionList.stringList());
                    break;
                case MARK:
                case UNMARK:
                    if (inputParts.length < 2) {
                        throw new DukeException(" Provide the task number.");
                    }
                    int taskNumber = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                    Task resultingTask;
                    String message;
                    if (command == Parser.Command.MARK) {
                        resultingTask = actionList.mark(taskNumber);
                        message = " Fine. Marked this task as done, you better not change your mind:\n";
                    } else {
                        resultingTask = actionList.unmark(taskNumber);
                        message = " Well, you changed your mind :(. Just this once:\n";
                    }
                    if (resultingTask != null) {
                        ui.lineSandwich(message + resultingTask);
                    } else {
                        ui.lineSandwich(" Task number does not exist, please review input.");
                    }
                    break;
                case TODO:
                    if (inputParts.length < 2 || inputParts[1].isEmpty()) {
                        throw new DukeException(" Provide the description. " +
                                "Format: todo task");
                    }
                    Todo task = new Todo(inputParts[1]);
                    actionList.add(task);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + task + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    if (inputParts.length < 2 || !inputParts[1].contains("/by")) {
                        throw new DukeException(" Event format incorrect. " +
                                "Format: deadline task /by date|time");
                    }
                    String[] deadlineParts = parser.splitByKeyword(inputParts[1], "/by");
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    actionList.add(deadline);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + deadline + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                case EVENT:
                    if (inputParts.length < 2 || !inputParts[1].contains("/from") || !inputParts[1].contains("/to")) {
                        throw new DukeException(" Event format incorrect. " +
                                "Format: event task /from date|time /to date|time");
                    }
                    String[] commandParts = parser.splitByKeyword(inputParts[1], "/from");
                    String[] eventParts = parser.splitByKeyword(commandParts[1], "/to");
                    Event event = new Event(commandParts[0], eventParts[0], eventParts[1]);
                    actionList.add(event);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + event + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                case DELETE:
                    if (inputParts.length < 2) {
                        throw new DukeException(" Please provide a task number. " +
                                "Format: delete task_number");
                    }
                    int taskNumberToDelete = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                    Task deletedTask = actionList.delete(taskNumberToDelete);
                    if (deletedTask != null) {
                        ui.lineSandwich(" Noted. I've removed this task:\n  " + deletedTask + "\n Now you have " + actionList.size() + " tasks in the list.");
                    } else {
                        throw new DukeException(" Task does not exist, please review input");
                    } break;
                default:
                    ui.lineSandwich(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            } catch (DukeException ohno) {
                ui.lineSandwich(ohno.getMessage());
            }
            input = ui.readInput();
            command = parser.getCommand(input);
        }
        savior.saveTasks(actionList.list());
        ui.lineSandwich(" Finally, I can rest.");
    }

    public static void main(String[] args) {
        Duke Whelmed = new Duke();
        Whelmed.initiateChatbot();
    }
}