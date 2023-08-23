import java.util.Scanner;
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    public void initiateChatbot() {
        ui.openingMessage();
        String input = ui.readInput();
        Parser.Command command = parser.getCommand(input);
        while (command != Parser.Command.BYE) {
            String[] inputParts = parser.splitInput(input);
            switch (command) {
                case LIST:
                    ui.lineSandwich(actionList.stringList());
                    break;
                case MARK:
                case UNMARK:
                    int taskNumber = Integer.parseInt(inputParts[1].split(" ")[0]) - 1;
                    Task resultingTask;
                    String message;
                    if (command == Parser.Command.MARK) {
                        resultingTask = actionList.mark(taskNumber);
                        message = " Fine. Marked this task as done, you better not change your mind:\n";
                    } else {
                        resultingTask = actionList.unmark(taskNumber);
                        message = " Well, you changed your mind :(. Just this once:\n";
                    } if (resultingTask != null) {
                        ui.lineSandwich(message + resultingTask);
                    } else {
                        ui.lineSandwich(" Task number does not exist, please re-learn arithmetic.");
                    }
                    break;
                case TODO:
                    Todo task = new Todo(inputParts[1]);
                    actionList.add(task);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + task + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    String[] deadlineParts = parser.splitByKeyword(inputParts[1], "/by");
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    actionList.add(deadline);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + deadline + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                case EVENT:
                    String[] commandParts = parser.splitByKeyword(inputParts[1], "/from");
                    String[] eventParts = parser.splitByKeyword(commandParts[1], "/to");
                    Event event = new Event(commandParts[0], eventParts[0], eventParts[1]);
                    actionList.add(event);
                    ui.lineSandwich(" Got it. I've added this task:\n  " + event + "\n Now you have " + actionList.size() + " tasks in the list.");
                    break;
                default:
                    Task simpleTask = new Task(input);
                    actionList.add(simpleTask);
                    ui.lineSandwich(" added: " + simpleTask);
                    break;
            }
            input = ui.readInput();
            command = parser.getCommand(input);
        } ui.lineSandwich(" Finally, I can rest.");
    }

    public static void main(String[] args) {
        Duke Whelmed = new Duke();
        Whelmed.initiateChatbot();
    }
}