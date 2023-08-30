public class UnmarkCommand extends Command {
  private int idx;

  public UnmarkCommand(int idx) {
    this.idx = idx;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws JoeIndexOutOfBoundsException {
    if (idx < 0 || idx > tasks.size()) {
      throw new JoeIndexOutOfBoundsException(idx);
    }

    tasks.get(idx - 1).markAsNotDone();
    ui.print(String.format("OK! I've marked this task as not done:%n %s", tasks.get(idx - 1)));

    storage.saveToFile(tasks);
  }
}
