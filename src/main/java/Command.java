public abstract class Command {
  protected boolean isExit = false;

  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JoeIndexOutOfBoundsException;
  public boolean isExit() {
    return isExit;
  }
}

