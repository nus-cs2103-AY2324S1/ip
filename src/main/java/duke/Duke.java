package duke;

import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (DukeException e) {
            this.ui.loadingErrorMessage();
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }
    public void start() {
        try {
            this.ui.introMessage();
            while (true) {
                String command = ui.getInput();
                if (command.equalsIgnoreCase("bye")) {
                    break;
                }
                this.parser.parse(command);
            }
            this.ui.closeScanner();
            this.storage.writeTasks(this.tasks.getTasks());
            this.ui.byeMessage();
        } catch (DukeException e) {
            System.out.println("OOPS!" + e.toString().split("DukeException:")[1]);
        }
    }

    public static void main(String[] args){
        Duke duke = new Duke();
        duke.start();
    }
}
