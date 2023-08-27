
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

  public Command(Printer out, TaskList taskList) {
    this.out = out;
    this.taskList = taskList;
  }

  public abstract void execute();
}
