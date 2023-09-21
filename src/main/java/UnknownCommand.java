public class UnknownCommand extends Command {
    @Override
    public void executeCommand(UI ui, Actions actionList) {
        ui.lineSandwich(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
