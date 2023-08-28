package duke;

import duke.task.TaskList;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }
    public void start() throws DukeException {
        ui.showWelcomeMessage();
        while (true) {
            String command = ui.getUserInput();
            if (command.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                ui.closeScanner();
                break;
            } else {
                try {
                    Parser.parse(command, tasks, ui);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        storage.saveTasks(tasks.getTasks());
    }


    public static void main(String[] args) throws DukeException {
       new Duke().start();
    }
}

