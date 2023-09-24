package ekud;

import java.io.OutputStream;

import ekud.state.State;
import ekud.storage.Storage;
import ekud.ui.Ui;
import ekud.ui.cli.Cli;
import ekud.ui.gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Ekud extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Storage storage = new Storage();

        // Load previous commands by using an isolated Program instance.
        State state = null;
        {
            Ui ui = new Cli(storage.createInputStream(), OutputStream.nullOutputStream(),
                    OutputStream.nullOutputStream());
            Program program = new Program(ui, null);
            program.run();
            state = program.getState();
        }

        Ui ui = new Gui(stage);
        Program program = new Program(ui, storage, state);
        program.run();
    }
}
