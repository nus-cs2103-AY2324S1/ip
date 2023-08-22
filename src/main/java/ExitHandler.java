public class ExitHandler implements ICommandHandler{

    @Override
    public void execute() {
        Main.getInstance().say("Bye. Hope to see you again soon!");
        Main.getInstance().exit();
    }
}
