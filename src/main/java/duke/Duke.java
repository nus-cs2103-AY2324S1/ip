package duke;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserInput();
                isExit = Parser.checkCommandType(fullCommand, tasks, ui);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showExit();
    }

}

