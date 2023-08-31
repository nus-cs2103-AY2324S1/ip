import java.io.IOException;
public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks ,Ui ui, Storage storage) {
        Ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        Ui.showLine();
        try{
            storage.writeTasksToFile(tasks.getTasks());
        } catch (IOException e) {
            throw new DukeException("Error in writing taskList to file!");
        }
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
