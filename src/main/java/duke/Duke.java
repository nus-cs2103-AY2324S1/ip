package duke;

import java.util.Scanner;

/**
 * A bot that allows creation, deletion of tasks.
 * It also can mark and unmarked tasks as completed.
 */
public class Duke {

    private UI ui = new UI();

    private Storage storage = new Storage("./data/Duke.txt");

    private CommandParser parser = new CommandParser();

    private TaskList taskList = new TaskList(storage.loadFile());

    private void handleUI() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (true) {
            try {
                command = scanner.nextLine();
                ui.printLine();
                Action action = parser.parseCommand(command);
                if (!action.execute(taskList, storage)) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            ui.printLine();
        }
        ui.bye();
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handleUI();
    }
}
