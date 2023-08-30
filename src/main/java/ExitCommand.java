public class ExitCommand extends Command{
    public void execute(TaskList tasks, Ui ui) {
        // no interaction with tasks
        ui.showMessage("Ta-da! It's time to go~ Keep smiling till we reunite!");
    }
    @Override
    public boolean isExit(){
        return true;
    }
}
