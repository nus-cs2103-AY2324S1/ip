package ekud;

import ekud.command.Command;
import ekud.command.ByeCommand;
import ekud.error.EkudException;
import ekud.handler.Handler;
import ekud.state.State;
import ekud.storage.Storage;
import ekud.ui.Ui;
import ekud.util.Pair;

public final class Program {
    private final Ui ui;
    private final Storage storage;
    private final State state;
    private final Handler handler;

    public Program(Ui ui) {
        this(ui, null);
    }

    public Program(Ui ui, Storage storage) {
        this(ui, storage, new State());
    }

    public Program(Ui ui, Storage storage, State state) {
        this.ui = ui;
        this.storage = storage;
        this.state = state;
        handler = new Handler();
    }

    public State getState() {
        return state;
    }

    public boolean step() {
        try {
            Pair<Command, Boolean> result = ui.readCommand();
            Command command = result.getFirst();
            boolean shouldContinue = result.getSecond();
            if (!shouldContinue) {
                return false;
            }
            if (!handler.handle(state, command, ui)) {
                return false;
            }
            if (!(command instanceof ByeCommand) && storage != null) {
                storage.write(command);
            }
        } catch (EkudException error) {
            ui.showError(error);
        }
        return true;
    }

    public void run() {
        ui.showGreeting();
        while (step()) {
        }
        ui.showFarewell();
    }
}
