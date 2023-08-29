import java.io.IOException;

public abstract class Command {
  static final String LIST = "list";
  static final String MARK = "mark";
  static final String UNMARK = "unmark";
  static final String TODO = "todo";
  static final String DEADLINE = "deadline";
  static final String EVENT = "event";
  static final String DELETE = "delete";

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
		} catch(DukeException e) {
			out.print(e);
			out.flush();
			return;
		} catch(DukeSideEffectException e) {
			out.print(e);
		}

		if(saveAfterAction) {
			try {
				savefile.write(taskList.toString());
			} catch (IOException e) {
				out.print(new DukeSideEffectException(String.format("Unable to write to savefile %s", savefile.getFilename())));
			}
		}

		out.flush();
	}

  public abstract void action();
}
