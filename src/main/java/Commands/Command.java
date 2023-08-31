package Commands;

public abstract class Command {

  public Command() {}

  public void execute(TaskList tasklist, Ui ui, Storage storage) {
    
  }

  public boolean isExit() {
    return false;
  }
  
}
