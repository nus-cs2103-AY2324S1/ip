package duke.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import duke.task.Task;

/**
 * A Printer class that helps format the output to the terminal, cache output and decide to print or
 * not.
 */
public class Printer {
    private static final String exceptionPrepend = "☹ OOPS!!!";

    private ArrayList<String> messages = new ArrayList<>();
    private Optional<String> error = Optional.empty();

    /**
     * Stores the output to print. Note that the output is cached and not printed straight away for
     * formatting purposes.
     */
    public void print(String... arr) {
        for (Object o : arr) {
            messages.add(o.toString());
        }
    }

    /** Stores the output to print. A DukeException would cause all previous output to be removed. */
    public void print(DukeException e) {
        error = Optional.of(String.format("%s %s", exceptionPrepend, e.getMessage()));
    }

    /**
     * Stores the output to print. A DukeSideEffectException would just store the output as the
     * operation can still be continued.
     */
    public void print(DukeSideEffectException e) {
        messages.add(String.format("%s %s", exceptionPrepend, e.getMessage()));
    }

    /**
     * Prints a stream of tasks
     *
     * @param tasks The tasks to print
     */
    public void print(Stream<Task> tasks) {
        print("Here are the tasks in your list:");
        List<Task> l = tasks.collect(Collectors.toList());
        IntStream.range(0, l.size()).forEach((i) -> print(String.format("%d.%s", i + 1, l.get(i))));
    }

    /**
     * Prints the output to the terminal. In the event of a DukeException, print the DukeException
     * only instead.
     */
    public void flush() {
        String line = "  ____________________________________________________________";
        System.out.println(line);
        error.ifPresentOrElse((s) -> {
            System.out.println(String.format("    %s", s));
        }, () -> {
            messages.forEach((s) -> {
                System.out.printf("    %s\n", s);
            });
        });
        System.out.println(line);

        reset();
    }

    private void reset() {
        messages.clear();
        error = Optional.empty();
    }

    public String getOutput() {
        if (error.isPresent()) {
            String s = error.orElseGet(() -> "");
            reset();
            return s;
        }
        String s = String.join("\n", messages.toArray(new String[0]));
        reset();
        return s;
    }
}
