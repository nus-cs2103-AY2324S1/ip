public class WelcomeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.showWelcome();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
