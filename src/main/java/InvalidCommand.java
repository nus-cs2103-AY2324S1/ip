public class InvalidCommand extends Command {
  private final String msg;

  public InvalidCommand(String msg) {
    this.msg = msg;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.print(msg);
  }
}
