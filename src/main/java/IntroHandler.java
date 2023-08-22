public class IntroHandler implements ICommandHandler{

    @Override
    public void execute() {
        String name = Main.getInstance().getName();
        Main.getInstance().say("Hello! I'm " + name, false);
        Main.getInstance().say("What can I do for you?");
    }
}
