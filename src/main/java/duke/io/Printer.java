package duke.io;

import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A Printer class that helps format the output to the terminal, cache output
 * and decide to print or
 * not.
 */
public class Printer {
	private static final String exceptionPrepend = "☹ OOPS!!!";

	ArrayList<String> messages = new ArrayList<>();
	Optional<String> error = Optional.empty();

	/**
	 * Stores the output to print. Note that the output is cached and not printed
	 * straight away for
	 * formatting purposes.
	 */
	public void print(String... arr) {
		for (Object o : arr)
			messages.add(o.toString());
	}

	/**
	 * Stores the output to print. A DukeException would cause all previous output
	 * to be removed.
	 */
	public void print(DukeException e) {
		error = Optional.of(String.format("%s %s", exceptionPrepend, e.getMessage()));
	}

	/**
	 * Stores the output to print. A DukeSideEffectException would just store the
	 * output as the
	 * operation can still be continued.
	 */
	public void print(DukeSideEffectException e) {
		messages.add(String.format("%s %s", exceptionPrepend, e.getMessage()));
	}

	/**
	 * Stores the output to print. Note that the output is cached and not printed
	 * straight away for
	 * formatting purposes.
	 */
	public void print(List<String> l) {
		l.forEach((s) -> print(s));
	}

	/**
	 * Prints the output to the terminal. In the event of a DukeException, print the
	 * DukeException
	 * only instead.
	 */
	public void flush() {
		String line = "  ____________________________________________________________";
		System.out.println(line);
		error.ifPresentOrElse(
				(s) -> {
					System.out.println(String.format("    %s", s));
				},
				() -> {
					messages.forEach(
							(s) -> {
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
}
