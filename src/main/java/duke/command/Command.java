package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeSideEffectException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;
import java.io.IOException;

public abstract class Command {
  public static final String LIST = "list";
  public static final String MARK = "mark";
  public static final String UNMARK = "unmark";
  public static final String TODO = "todo";
  public static final String DEADLINE = "deadline";
  public static final String EVENT = "event";
  public static final String DELETE = "delete";
	public static final String FIND = "find";

  protected Printer out;
  protected TaskList taskList;
  private FileIO savefile;

  private boolean saveAfterAction = false;

  public Command(Printer out, TaskList taskList, FileIO savefile) {
    this.out = out;
    this.taskList = taskList;
    this.savefile = savefile;
  }

  public final void save() {
    this.saveAfterAction = true;
  }

  public final void execute() {
    try {
      action();
    } catch (DukeException e) {
      out.print(e);
      out.flush();
      return;
    } catch (DukeSideEffectException e) {
      out.print(e);
    }

    if (saveAfterAction) {
      try {
        savefile.write(taskList.toString());
      } catch (IOException e) {
        out.print(
            new DukeSideEffectException(
                String.format("Unable to write to savefile %s", savefile.getFilename())));
      }
    }

    out.flush();
  }

  public abstract void action();
}
