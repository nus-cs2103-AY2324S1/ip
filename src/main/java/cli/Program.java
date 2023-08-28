package cli;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import action.Action;
import action.ByeAction;
import error.ArgumentException;
import error.ParseException;
import reducer.Reducer;
import state.State;
import util.Pair;

public final class Program {
    private final Scanner scanner;
    private final PrintStream out;
    private final PrintStream save;
    private final Reducer reducer;

    public Program(InputStream in) {
        this(in, OutputStream.nullOutputStream(), OutputStream.nullOutputStream());
    }

    public Program(InputStream in, OutputStream out) {
        this(in, out, OutputStream.nullOutputStream());
    }

    public Program(InputStream in, OutputStream out, OutputStream save) {
        this.scanner = new Scanner(in);
        this.out = new PrintStream(out);
        this.save = new PrintStream(save);
        this.reducer = new Reducer();
    }

    public Pair<Pair<State, State>, Boolean> step(State state) {
        out.print("> ");

        String line;
        try {
            line = scanner.nextLine().trim();
        } catch (NoSuchElementException error) {
            out.println();
            return new Pair<>(new Pair<>(state, state), false);
        }
        if (line.isEmpty()) {
            return new Pair<>(new Pair<>(state, state), true);
        }

        State newState = state;
        boolean isRunning = true;
        try {
            Action action = Parser.parseAction(line);
            Pair<State, Boolean> result = reducer.run(state, action, out);
            if (!(action instanceof ByeAction)) {
                save.println(action);
            }
            newState = result.getFirst();
            isRunning = result.getSecond();
        } catch (ParseException error) {
            out.println("☹ OOPS!!! " + error.getMessage());
        } catch (ArgumentException error) {
            out.println("☹ OOPS!!! " + error.getMessage());
        }

        return new Pair<>(new Pair<>(state, newState), isRunning);
    }

    public State run(State state) {
        out.println("Hello! I'm Ekud!");
        out.println("What can I do for you?");

        State currentState = state;
        while (true) {
            Pair<Pair<State, State>, Boolean> result = step(currentState);
            if (!result.getSecond()) {
                break;
            }
            currentState = result.getFirst().getSecond();
        }

        out.println("Bye. Hope to see you again soon!");

        return currentState;
    }

    public State run() {
        return run(new State());
    }

    public void close() {
        scanner.close();
    }
}
