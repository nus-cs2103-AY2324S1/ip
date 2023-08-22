import java.util.Scanner;
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    public void initiateChatbot() {
        ui.openingMessage();
        String input = ui.readInput();
        while (!input.toLowerCase().equals("bye")) {
            if (input.toLowerCase().equals("list")) {
                ui.lineSandwich(actionList.stringList());
            } else if (input.toLowerCase().startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                Task markedTask = actionList.mark(taskNumber);
                if (markedTask != null) {
                    ui.lineSandwich("Fine. Marked this task as done, you better not change your mind:\n"
                            + markedTask);
                } else {
                    ui.lineSandwich("Task number does not exist, please re-learn arithmetic.");
                }
            } else if (input.toLowerCase().startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                Task unmarkedTask = actionList.unmark(taskNumber);
                if (unmarkedTask != null) {
                    ui.lineSandwich("Well, you changed your mind :(. Just this once:\n"
                            + unmarkedTask);
                } else {
                    ui.lineSandwich("Task number does not exist, please re-learn arithmetic.");
                }
            } else {
                actionList.add(input);
                ui.lineSandwich("added: " + input);
            } input = ui.readInput();
        } ui.lineSandwich("Finally, I can rest.");
    }

    public static void main(String[] args) {
        Duke Whelmed = new Duke();
        Whelmed.initiateChatbot();
    }
}