package ekud;

import java.io.OutputStream;

import ekud.state.State;
import ekud.storage.Storage;
import ekud.ui.Ui;
import ekud.ui.cli.Cli;

public class Ekud {
    public static void main(String[] args) {
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

        Ui ui = new Cli(System.in, System.out, System.err);
        Program program = new Program(ui, storage, state);
        program.run();
    }
}
