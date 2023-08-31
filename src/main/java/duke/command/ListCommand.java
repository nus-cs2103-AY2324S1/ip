package duke.command;

import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListCommand extends Command {
  private String before;

  public ListCommand(Printer out, TaskList taskList, FileIO file, String before) {
    super(out, taskList, file);
    this.before = before;
  }

  @Override
  public void action() {
    Optional<LocalDateTime> beforeTime = Optional.empty();
    if (!before.equals("")) {
      try {
        beforeTime = Optional.of(DatetimeHelper.parse(before));
      } catch (DateTimeParseException e) {
        throw new InvalidDatetimeFormatException("before", "list");
      }
    }

		out.print(taskList.filter(beforeTime));
  }
}
