public class ListCommand extends Command {
  public ListCommand(Printer out, TaskList taskList, FileIO file) {
    super(out, taskList, file);
  }

  @Override
  public void action() {
    String toPrint[] = new String[taskList.size() + 1];
    toPrint[0] = "Here are the tasks in your list:";
    for (int i = 1; i <= taskList.size(); ++i) {
      toPrint[i] = String.format("%d.%s", i, taskList.getTask(i).toString());
    }
    out.print(toPrint);
  }
}
