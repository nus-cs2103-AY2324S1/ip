/**
 * Represents a command to exit the chatbot
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList);
        ui.printToScreen("Goodbye!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
