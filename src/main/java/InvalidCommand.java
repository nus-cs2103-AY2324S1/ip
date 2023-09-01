public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printInvalidMessage();
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
