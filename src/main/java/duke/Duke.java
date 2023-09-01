package duke;

import command.Command;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private UI ui;
    private static final String NAME = "DEREK";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI(NAME);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (FileNotFoundException e) {
            ui.printLoadingErrorMessage();
            this.tasks = new TaskList();
        }

    }

    public void run() {
        ui.printWelcomeMessage();
        String input;

        while (true) {
            input = ui.readCommand();
            try {
                Command command = Parser.parseUserInput(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/state.txt").run();
    }
}
