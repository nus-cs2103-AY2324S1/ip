package duke.io;

import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Printer {
  private static final String exceptionPrepend = "☹ OOPS!!!";

  ArrayList<String> messages = new ArrayList<>();
  Optional<String> error = Optional.empty();

  public void print(String... arr) {
    for (Object o : arr) messages.add(o.toString());
  }

  public void print(DukeException e) {
    error = Optional.of(String.format("%s %s", exceptionPrepend, e.getMessage()));
  }

  public void print(DukeSideEffectException e) {
    messages.add(String.format("%s %s", exceptionPrepend, e.getMessage()));
  }

	public void print(Stream<Task> tasks) {
		print("Here are the tasks in your list:");
		List<Task> l = tasks.collect(Collectors.toList());
		IntStream.range(0, l.size()).forEach((i) -> print(String.format("%d.%s", i+1, l.get(i))));
	}

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
