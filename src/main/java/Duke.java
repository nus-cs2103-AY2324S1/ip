import duke.exception.DukeException;
import duke.processors.*;

public class Duke {
     private final Ui ui;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        TaskList tasks = new TaskList(new FileHandler());
        parser = new Parser(tasks);
    }

    private void run() {
        ui.OnEnter();
        new FileHandler().fileCreate();
        while (!parser.getTerminate()) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                parser.readInputs(fullCommand);
            } catch (DukeException e) {
                ui.showLine();
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.OnExit();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
