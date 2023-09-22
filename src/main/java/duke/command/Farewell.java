package duke.command;

import duke.Ui;

/** Abstraction to bid farewell to the user. */
public class Farewell extends Command {

    private Ui ui = new Ui();

    public Farewell() {}

    @Override
    public String execute() {
        return this.ui.farewell();
    }
}
