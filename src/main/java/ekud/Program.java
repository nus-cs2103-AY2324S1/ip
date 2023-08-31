package ekud;

import ekud.command.Command;
import ekud.error.EkudException;
import ekud.handler.Handler;
import ekud.state.State;
import ekud.storage.Storage;
import ekud.ui.Ui;
import ekud.util.Pair;

/**
 * Represents the abstract ekud program, decoupled from input and output
 * implementations.
 */
public final class Program {
    private final Ui ui;
    private final Storage storage;
    private final State state;
    private final Handler handler;

    /**
     * Creates a new program that doesn't store data and with an empty state.
     * 
     * @param ui The user interface implementation.
     */
    public Program(Ui ui) {
        this(ui, null);
    }

    /**
     * Creates a new program that with an empty state.
     * 
     * @param ui      The user interface implementation.
     * @param storage The storage implementation.
     */
    public Program(Ui ui, Storage storage) {
        this(ui, storage, new State());
    }

    /**
     * Creates a new program starting with the given state.
     * 
     * @param ui      The user interface implementation.
     * @param storage The storage implementation.
     * @param state   The current state of the program.
     */
    public Program(Ui ui, Storage storage, State state) {
        this.ui = ui;
        this.storage = storage;
        this.state = state;
        handler = new Handler();
    }

    /**
     * Returns the current state of the program.
     * 
     * @return The current state.
     */
    public State getState() {
        return state;
    }

    /**
     * Steps the program forward by one command, reading the command from the user
     * interface and handling it.
     * 
     * @return Whether the program should continue or end execution.
     */
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
            if (storage != null) {
                storage.write(command);
            }
        } catch (EkudException error) {
            ui.showError(error);
        }
        return true;
    }

    /**
     * Runs the program by stepping until execution is ended.
     */
    public void run() {
        ui.showGreeting();
        while (step()) {
        }
        ui.showFarewell();
    }
}
