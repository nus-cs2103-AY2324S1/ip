package duke;

import java.util.Scanner;
import duke.task.TaskList;

public class Duke {

    private final String name = "Paimon";
    private TaskList taskList = new TaskList();

    public void greetAndFarewell() {
        Ui.greetings();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            Ui.showLine();
            try {
                Parser.readTask(command, taskList);
            } catch (DukeException e) {
                Ui.showError(e);
            }
            Ui.showLine();
            command = scanner.nextLine();
        }

        scanner.close();
        Ui.exit();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greetAndFarewell();
    }
}
